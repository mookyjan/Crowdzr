package com.example.mudassirkhan.crowdzr.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder{
   public TextView txtReviewerName,txtReviewDetail;
   public RatingBar ratingBar;
   public ImageView imageReview;


    public ReviewViewHolder(View itemView) {
        super(itemView);
         txtReviewerName=(TextView)itemView.findViewById(R.id.txtReviewerName);
         txtReviewDetail=(TextView)itemView.findViewById(R.id.txtReviewDetail);
         ratingBar=(RatingBar)itemView.findViewById(R.id.ratingbarReview);
         imageReview=(ImageView)itemView.findViewById(R.id.img_review);
    }
}
