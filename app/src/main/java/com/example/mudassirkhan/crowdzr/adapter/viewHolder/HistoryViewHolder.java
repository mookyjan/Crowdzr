package com.example.mudassirkhan.crowdzr.adapter.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {


    public TextView txtItemTitle, txtItemPrice, txtItemDate, txtItemStatus;
    public CardView cardView;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardItemProfile);
        txtItemTitle = (TextView) itemView.findViewById(R.id.txt_item_title);
        txtItemPrice = (TextView) itemView.findViewById(R.id.txt_item_price);
        txtItemDate = (TextView) itemView.findViewById(R.id.txt_item_date);
        txtItemStatus = (TextView) itemView.findViewById(R.id.txt_item_status);
    }
}
