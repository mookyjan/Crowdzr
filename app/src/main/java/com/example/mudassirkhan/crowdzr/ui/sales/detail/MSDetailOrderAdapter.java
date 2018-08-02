package com.example.mudassirkhan.crowdzr.ui.sales.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.model.managesales.ResponseOrderDetail;

import java.util.List;

public class MSDetailOrderAdapter extends RecyclerView.Adapter<MSDetailOrderAdapter.MSDetailOrderViewHolder>{

    List<ResponseOrderDetail> mOrderDetailList;

    public MSDetailOrderAdapter(List<ResponseOrderDetail> mOrderDetailList){
        this.mOrderDetailList=mOrderDetailList;
    }
    @NonNull
    @Override
    public MSDetailOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_order_detail,parent,false);
        return new MSDetailOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MSDetailOrderViewHolder holder, int position) {

        ResponseOrderDetail responseOrderDetail=mOrderDetailList.get(position);

        holder.onBind(responseOrderDetail);

    }

    @Override
    public int getItemCount() {
        return mOrderDetailList.size();
    }


    public class MSDetailOrderViewHolder extends RecyclerView.ViewHolder{
        TextView txtDate,txtTime,txtDetail,txtLocation;
        public MSDetailOrderViewHolder(View itemView) {
            super(itemView);
            txtDate=(TextView)itemView.findViewById(R.id.txt_order_date);
            txtTime=(TextView)itemView.findViewById(R.id.txt_order_time);
            txtDetail=(TextView)itemView.findViewById(R.id.txt_order_detail);
            txtLocation=(TextView)itemView.findViewById(R.id.txt_order_location);
        }

        public void onBind(ResponseOrderDetail responseOrderDetail){
            txtDate.setText(responseOrderDetail.getStrDate());
            txtTime.setText(responseOrderDetail.getStrTime());
            txtDetail.setText(responseOrderDetail.getStrDetail());
            txtLocation.setText(responseOrderDetail.getStrLocation());
        }
    }
}
