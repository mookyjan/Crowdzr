package com.example.mudassirkhan.crowdzr.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.model.managesales.ResponseMSRequest;

import java.util.List;

public class MSRequestAdapter extends RecyclerView.Adapter<MSRequestAdapter.MSRequestViewHolder>{

    private OnItemClickListener mOnItemClickListener;
    private List<ResponseMSRequest> mResponseMSRequestList;

    public MSRequestAdapter(List<ResponseMSRequest> mResponseMSRequestList,OnItemClickListener mOnItemClickListener){
        this.mResponseMSRequestList=mResponseMSRequestList;
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @NonNull
    @Override
    public MSRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_manage_sales,parent,false);
        return new MSRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MSRequestViewHolder holder, int position) {

        ResponseMSRequest responseMSRequest=mResponseMSRequestList.get(position);
        holder.onBind(responseMSRequest);
    }

    @Override
    public int getItemCount() {
        return mResponseMSRequestList.size();
    }


    public interface OnItemClickListener{
        public void onItemClick(ResponseMSRequest responseMSRequest);
    }

    public class MSRequestViewHolder extends RecyclerView.ViewHolder{

        TextView txtUserName,txtItemTitle,txtDate,txtPrice;
        ImageView imgUserProfile;
        public MSRequestViewHolder(View itemView) {
            super(itemView);
            imgUserProfile=(ImageView)itemView.findViewById(R.id.imgProfile);
            txtUserName=(TextView)itemView.findViewById(R.id.txt_user_name);
            txtItemTitle=(TextView)itemView.findViewById(R.id.txt_item_name);
            txtDate=(TextView)itemView.findViewById(R.id.txt_date);
            txtPrice=(TextView)itemView.findViewById(R.id.txt_item_price);
        }

        public void onBind(ResponseMSRequest responseMSRequest){

            txtUserName.setText(responseMSRequest.getTxtUserName());
            txtItemTitle.setText(responseMSRequest.getTxtItemName());
            txtDate.setText(responseMSRequest.getTxtDate());
            txtPrice.setText(responseMSRequest.getTxtPrice());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(responseMSRequest);
                }
            });
        }


    }
}
