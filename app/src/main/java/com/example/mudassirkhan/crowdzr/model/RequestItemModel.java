package com.example.mudassirkhan.crowdzr.model;

import android.widget.ImageView;
import android.widget.TextView;

public class RequestItemModel {
    private int imageView;
    private String  txtTitle,txtPrice,txtDetail,txtLikes,txtViews;
    public RequestItemModel(String txtTitle,String txtPrice,String txtDetail,String txtLikes,String txtViews){
        this.txtTitle=txtTitle;
        this.txtPrice=txtPrice;
        this.txtDetail=txtDetail;
        this.txtLikes=txtLikes;
        this.txtViews=txtViews;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public String getTxtDetail() {
        return txtDetail;
    }

    public void setTxtDetail(String txtDetail) {
        this.txtDetail = txtDetail;
    }

    public String getTxtLikes() {
        return txtLikes;
    }

    public void setTxtLikes(String txtLikes) {
        this.txtLikes = txtLikes;
    }

    public String getTxtViews() {
        return txtViews;
    }

    public void setTxtViews(String txtViews) {
        this.txtViews = txtViews;
    }
}
