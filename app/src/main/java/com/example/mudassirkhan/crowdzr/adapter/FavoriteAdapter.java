package com.example.mudassirkhan.crowdzr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.viewHolder.RequestViewHolder;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<RequestViewHolder> {

    private List<RequestItemModel> mFavoriteItemModelList;
    private Context mContext;
    RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    public FavoriteAdapter(Context context,List<RequestItemModel> mRequestItemModelList,RecyclerViewClickedInterface mRecyclerViewClickedInterface){
        this.mContext=context;
        this.mFavoriteItemModelList=mRequestItemModelList;
        this.mRecyclerViewClickedInterface=mRecyclerViewClickedInterface;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.single_item_favorite,parent,false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, final int position) {

        RequestItemModel requestItemModel=mFavoriteItemModelList.get(position);

        holder.txtItemTitle.setText(requestItemModel.getTxtTitle());
        holder.txtItemPrice.setText(requestItemModel.getTxtPrice());
        holder.txtItemDetail.setText(requestItemModel.getTxtDetail());
        holder.txtItemLikes.setText(requestItemModel.getTxtLikes());
        holder.txtItemViews.setText(requestItemModel.getTxtViews());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewClickedInterface.onListItemClicked(position,v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFavoriteItemModelList.size();
    }
}
