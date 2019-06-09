package ru.goodibunakov.abbyy_test.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.goodibunakov.abbyy_test.MainActivity;
import ru.goodibunakov.abbyy_test.R;
import ru.goodibunakov.abbyy_test.adapters.HistoryAdapter;
import ru.goodibunakov.abbyy_test.model.DatabaseModel;

public class HistoryFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private HistoryAdapter adapter;
    private List<DatabaseModel> list = new ArrayList<>();

    public HistoryFragment() {
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HistoryAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) Objects.requireNonNull(getActivity())).dBHelper.tableIsExist()) {
            list = ((MainActivity) Objects.requireNonNull(getActivity())).dBHelper.getCardsFromDb();
            Collections.reverse(list);
            Log.d("debug", "list = " + list.toString());
            adapter.update(list);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}