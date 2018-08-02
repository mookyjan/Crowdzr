package com.example.mudassirkhan.crowdzr.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;

public class InboxViewHolder extends RecyclerView.ViewHolder {

   public ImageView imgProfile,imgItemType;
   public TextView txtUserName,txtItemName,txtTime,txtDetail;
    public InboxViewHolder(View itemView) {
        super(itemView);
        imgProfile=(ImageView)itemView.findViewById(R.id.imgProfileInbox);
        txtUserName=(TextView)itemView.findViewById(R.id.txtUserNameInbox);
        txtItemName=(TextView)itemView.findViewById(R.id.txtItemNameInbox);
        txtTime=(TextView)itemView.findViewById(R.id.txtTimeInbox);
        txtDetail=(TextView)itemView.findViewById(R.id.txtMsgDetailInbox);
        imgItemType=(ImageView)itemView.findViewById(R.id.imgTypeInbox);
    }
}
