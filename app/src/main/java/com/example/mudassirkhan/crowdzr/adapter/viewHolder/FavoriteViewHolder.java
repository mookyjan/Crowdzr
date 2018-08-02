package com.example.mudassirkhan.crowdzr.adapter.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView txtItemTitle,txtItemPrice,txtItemDetail,txtItemLikes,txtItemViews;
    public CardView cardView;
    public FavoriteViewHolder(View itemView) {
        super(itemView);
        cardView=(CardView)itemView.findViewById(R.id.cardItem);
        imageView=(ImageView)itemView.findViewById(R.id.imageViewItem);
        txtItemTitle=(TextView)itemView.findViewById(R.id.txt_item_title);
        txtItemPrice=(TextView)itemView.findViewById(R.id.txt_price);
        txtItemDetail=(TextView)itemView.findViewById(R.id.txt_item_detail);
        txtItemLikes=(TextView)itemView.findViewById(R.id.txt_item_like);
        txtItemViews=(TextView)itemView.findViewById(R.id.txt_item_views);
        }
}
