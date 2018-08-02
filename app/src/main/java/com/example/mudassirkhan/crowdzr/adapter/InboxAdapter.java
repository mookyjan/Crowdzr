package com.example.mudassirkhan.crowdzr.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.viewHolder.InboxViewHolder;
import com.example.mudassirkhan.crowdzr.model.InboxViewModel;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxViewHolder> {

    private List<InboxViewModel> mInboxModelList;

    public InboxAdapter(List<InboxViewModel> mInboxModelList){
        this.mInboxModelList=mInboxModelList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_inbox,parent,false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder holder, int position) {

        InboxViewModel inboxViewModel=mInboxModelList.get(position);

        holder.txtUserName.setText(inboxViewModel.getStrUserName());
        holder.txtItemName.setText(inboxViewModel.getStrItemName());
        holder.txtTime.setText(inboxViewModel.getStrTime());
        holder.txtDetail.setText(inboxViewModel.getStrItemDetail());
    }

    @Override
    public int getItemCount() {
        return mInboxModelList.size();
    }
}
