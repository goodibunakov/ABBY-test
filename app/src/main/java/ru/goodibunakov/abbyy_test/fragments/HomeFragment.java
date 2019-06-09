package ru.goodibunakov.abbyy_test.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.goodibunakov.abbyy_test.MainActivity;
import ru.goodibunakov.abbyy_test.R;
import ru.goodibunakov.abbyy_test.api.ApiUtils;
import ru.goodibunakov.abbyy_test.model.Body;
import ru.goodibunakov.abbyy_test.model.DatabaseModel;
import ru.goodibunakov.abbyy_test.model.Item;
import ru.goodibunakov.abbyy_test.model.Markup;
import ru.goodibunakov.abbyy_test.model.Markup2;
import ru.goodibunakov.abbyy_test.model.TranslateModel;
import ru.goodibunakov.abbyy_test.utils.Constants;

public class HomeFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.edittext)
    EditText enter;
    @BindView(R.id.btn_translate)
    MaterialButton btnTranslate;
    @BindView(R.id.card_home)
    CardView cardView;
    @BindView(R.id.txt_word)
    TextView title;
        @BindView(R.id.txt_p2_0)
    TextView text_p2_0;
//    @BindView(R.id.txt_p2_1)
//    TextView text_p2_1;
    @BindView(R.id.txt_p2_2)
    TextView text_p2_2;
    @BindView(R.id.txt_p2_3)
    TextView text_p2_3;
    @BindView(R.id.trans)
    TextView trans;
    @BindView(R.id.txt_int)
    TextView inter;

    private final long DAY_MILLIS = 86400000L;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_translate)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_translate:
                String word = enter.getText().toString().trim();
                if (!word.isEmpty()) {
                    enter.setText("");
                    if (!isEnglishSymbols(word)) {
                        Toast.makeText(getActivity(), getText(R.string.only_eng), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!checkIfAuthLive()) {
                        ApiUtils.getApiService()
                                .getAuthorization("Basic " + Constants.KEY_API)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Log.d("debug", "response.isSuccessful()");
                                            if (response.body() != null) {
                                                Log.d("debug", "response.body()" + response.body());
                                                String bearerToken = null;
                                                try {
                                                    bearerToken = response.body().string();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                if (bearerToken != null)
                                                    saveBearerTokenToSharedPref(bearerToken);
                                                Log.d("debug", "bearerToken = " + bearerToken);
                                                getTranslation(word);
                                            }
                                        } else {
                                            Log.d("debug", "response.isNOTSuccessful()");
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                        Toast.makeText(getActivity(), getText(R.string.auth_error), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        getTranslation(word);
                    }
                }
                break;
        }
    }

    private void getTranslation(String text) {
        String header = "Bearer " + getBearerTokenFromSharedPref();
        ApiUtils.getApiService()
                .getTranslation(header, text, 1033, 1049)
                .enqueue(new Callback<List<TranslateModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<TranslateModel>> call, @NonNull Response<List<TranslateModel>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_SHORT).show();
                                Log.d("OkHttp", "response.body()" + response.body());
                                saveTranslation(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<TranslateModel>> call, @NonNull Throwable t) {
                        Toast.makeText(getActivity(), getText(R.string.not_found), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveTranslation(List<TranslateModel> list) {
        String title = list.get(0).getTitle();
        String international = "";
        String internationalValues = "";
        List<String> translations = new ArrayList<>();

        // формирование второго пункта карточки
        String p2_0 = "";
        String p2_1 = "";
        String p2_2 = "";
        StringBuilder p2_3 = new StringBuilder();

        TranslateModel translateModel = list.get(0);
        List<Body> bodyList = translateModel.getBody();
        Log.d("debug", "bodyList.size() = " + bodyList.size());
        for (Body body : bodyList) {
            if (body.getNode().equals("Paragraph")) continue;

            List<Item> listItems = body.getListItems();
            for (int i = 0; i < listItems.size(); i++) {
                Item item1 = listItems.get(i);
                List<Markup2> markup = item1.getMarkup();
                for (int k = 0; k < markup.size(); k++) {
                    Markup2 markup2 = markup.get(k);
                    Log.d("debug", "markup2.getType() " + markup2.getType());
                    Log.d("debug", "markup2.getNode() " + markup2.getNode());
                    if (markup2.getNode().equals("Paragraph")) {
                        List<Markup> markup1 = markup2.getMarkup();
                        for (Markup markup3 : markup1) {
                            if (i == 0) {
                                if (markup3.getNode().equals("Abbrev")) {
                                    international = markup3.getText();
                                }
                                if (markup3.getNode().equals("Text")) {
                                    internationalValues = markup3.getText();
                                }
                            } else {
                                if (markup3.getFullText() != null && markup3.getFullText().contains("имя существительное") && markup3.getNode().equals("Abbrev")) {
                                    p2_0 = markup3.getText();
                                }
                                if (markup3.getNode().equals("Text")) p2_1 = markup3.getText();
                                if (markup3.getNode().equals("Abbrev") && markup3.getFullText().contains("множественное число"))
                                    p2_2 = markup3.getText();
                                if (markup3.getNode().equals("Text"))
                                    p2_3.append(markup3.getText());
                            }
                        }
                    } else if (body.getType() == 2) {
                        if (markup2.getListItems() != null) {
                            List<Item> listItems1 = markup2.getListItems();
                            Log.d("debug", "listItems1.get(0).getNode()" + listItems1.get(0).getNode());
                            Log.d("debug", "listItems1.get(0).getText()" + listItems1.get(0).getText());
                            for (Item item : listItems1) {
                                Log.d("debug", "item.getText()" + item.getText());
                                Log.d("debug", "item.getNode()" + item.getNode());
                                List<Markup2> markup11 = item.getMarkup();
                                for (Markup2 markup21 : markup11) {
                                    Log.d("debug", "markup21.getNode() " + markup21.getNode());
                                    Log.d("debug", "markup21.getNode() " + markup21.getType());
                                    Log.d("debug", "markup21.getNode() " + markup21.getText());
                                    Log.d("debug", "markup21.getNode() " + markup21.getListItems());
                                    List<Markup> markup33 = markup21.getMarkup();
//                                    Log.d("debug", "markup21.getMarkup().size() " + markup21.getMarkup().size());
//                                    for (Markup markup4 : markup33) {
//                                        Log.d("debug", "markup4.getText()" + markup4.getText());
//                                        Log.d("debug", "markup4.getNode()" + markup4.getNode());
//                                        if (markup4.getNode().equals("Text")) {
//                                            translations.add(markup4.getText());
//                                        }
//                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        Log.d("debug", "translations = " + translations.toString());
        Log.d("debug", "p2_0 = " + p2_0);
        Log.d("debug", "p2_1 = " + p2_1);
        Log.d("debug", "p2_2 = " + p2_2);
        Log.d("debug", "p2_3 = " + p2_3.toString());
        DatabaseModel databaseModel = new DatabaseModel(title, international, internationalValues, translations.toString(), p2_0, p2_1, p2_2, p2_3.toString());
        ((MainActivity) Objects.requireNonNull(getActivity())).saveToDb(databaseModel);
        showCardView(databaseModel);
        Log.d("OkHttp", "databaseModel = " + databaseModel.toString());
    }

    private void showCardView(DatabaseModel databaseModel) {
        cardView.setVisibility(View.GONE);
        title.setText(databaseModel.getTitle());
        trans.setText(databaseModel.getInternationalValue());
        inter.setText(databaseModel.getInternational());
        text_p2_0.setText(databaseModel.getP2_0());
//        text_p2_1.setText(databaseModel.getP2_1());
        text_p2_2.setText(databaseModel.getP2_2());
        text_p2_3.setText(databaseModel.getP2_3());
        if (cardView.getVisibility() != View.VISIBLE) {
            cardView.setAlpha(0f);
            cardView.setVisibility(View.VISIBLE);
            cardView.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);
        }
    }

    private boolean isEnglishSymbols(String word) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    private boolean checkIfAuthLive() {
        long date = getBearerTokenDateFromSharedPref();
        if (date == 0L) return false;
        return (System.currentTimeMillis() - date) <= DAY_MILLIS;
    }

    private void saveBearerTokenToSharedPref(String bearerToken) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferences.edit()
                .putString(Constants.BEARER_TOKEN, bearerToken)
                .putLong(Constants.BEARER_TOKEN_DATE, System.currentTimeMillis())
                .apply();
    }

    private String getBearerTokenFromSharedPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return preferences.getString(Constants.BEARER_TOKEN, "");
    }

    private Long getBearerTokenDateFromSharedPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return preferences.getLong(Constants.BEARER_TOKEN_DATE, 0L);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
