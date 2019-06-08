package ru.goodibunakov.abbyy_test.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.goodibunakov.abbyy_test.R;

public class HomeFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.edittext)
    EditText enter;
    @BindView(R.id.btn_translate)
    MaterialButton btnTranslate;

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
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_translate:
                String word = enter.getText().toString().trim();
                if (!word.isEmpty()){
                    enter.setText("");
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
