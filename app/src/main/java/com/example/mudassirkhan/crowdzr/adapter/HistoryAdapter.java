package com.example.mudassirkhan.crowdzr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.viewHolder.HistoryViewHolder;
import com.example.mudassirkhan.crowdzr.adapter.viewHolder.RequestViewHolder;
import com.example.mudassirkhan.crowdzr.model.HistoryItemModel;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private List<HistoryItemModel> mHistoryItemModelList;
    private Context mContext;
    RecyclerViewClickedInterface mRecyclerViewClickedInterface;

    public HistoryAdapter(Context context, List<HistoryItemModel> mRequestItemModelList, RecyclerViewClickedInterface mRecyclerViewClickedInterface) {
        this.mContext = context;
        this.mHistoryItemModelList = mRequestItemModelList;
        this.mRecyclerViewClickedInterface = mRecyclerViewClickedInterface;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_profile_card_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, final int position) {

        HistoryItemModel historyItemModel = mHistoryItemModelList.get(position);

        holder.txtItemTitle.setText(historyItemModel.getTxtTitle());
        holder.txtItemPrice.setText(historyItemModel.getTxtPrice());
        holder.txtItemDate.setText(historyItemModel.getTxtDate());
        holder.txtItemStatus.setText(historyItemModel.getTxtStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewClickedInterface.onListItemClicked(position, v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHistoryItemModelList.size();
    }
}