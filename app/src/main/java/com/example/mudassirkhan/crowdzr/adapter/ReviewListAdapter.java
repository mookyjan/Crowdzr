package com.example.mudassirkhan.crowdzr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.viewHolder.ReviewViewHolder;
import com.example.mudassirkhan.crowdzr.model.request.RequestReviewModel;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<RequestReviewModel> mRequestReviewModelList;
    private Context mContext;
    RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    public ReviewListAdapter(Context mContext,List<RequestReviewModel> mRequestReviewModelList,RecyclerViewClickedInterface mRecyclerViewClickedInterface){
        this.mContext=mContext;
        this.mRequestReviewModelList=mRequestReviewModelList;
        this.mRecyclerViewClickedInterface=mRecyclerViewClickedInterface;
    }
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.single_review_layout,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        RequestReviewModel requestReviewModel=mRequestReviewModelList.get(position);
        holder.imageReview.setImageResource(requestReviewModel.getImageView());
        holder.txtReviewerName.setText(requestReviewModel.getTxtName());
        holder.txtReviewDetail.setText(requestReviewModel.getTxtReviewDetail());
        holder.ratingBar.setRating(requestReviewModel.getReviewValue());

        holder.imageReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewClickedInterface.onListItemClicked(position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRequestReviewModelList.size();
    }
}
