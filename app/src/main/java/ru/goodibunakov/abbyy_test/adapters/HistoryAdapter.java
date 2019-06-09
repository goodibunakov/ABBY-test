package ru.goodibunakov.abbyy_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.goodibunakov.abbyy_test.R;
import ru.goodibunakov.abbyy_test.model.DatabaseModel;

public class HistoryAdapter extends RecyclerView.Adapter {

    private List<DatabaseModel> items;
    private int lastPosition = -1;
    private Context context;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView = layoutInflater.inflate(R.layout.card_item, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HistoryViewHolder) holder).bind(items.get(position));
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else
            return 0;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_word)
        TextView title;
        @BindView(R.id.txt_p2_0)
        TextView p2_0;
        @BindView(R.id.trans)
        TextView trans;
//        @BindView(R.id.txt_p2_1)
//        TextView p2_1;
        @BindView(R.id.txt_p2_2)
        TextView p2_2;
        @BindView(R.id.txt_p2_3)
        TextView p2_3;
        @BindView(R.id.txt_int)
        TextView inter;

        HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(DatabaseModel item) {
            trans.setText(item.getInternationalValue());
            inter.setText(item.getInternational());
            p2_0.setText(item.getP2_0());
//            p2_1.setText(item.getP2_1());
            p2_2.setText(item.getP2_2());
            p2_3.setText(item.getP2_3());
            title.setText(item.getTitle());
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void update(List<DatabaseModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
