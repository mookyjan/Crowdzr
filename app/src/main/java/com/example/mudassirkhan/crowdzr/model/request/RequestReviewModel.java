package com.example.mudassirkhan.crowdzr.model.request;

public class RequestReviewModel {

    int imageView;
    String txtName,txtReviewDetail;
    float reviewValue;

    public RequestReviewModel(int imageId,String txtName,float reviewValue,String txtReviewDetail){
        this.imageView=imageId;
        this.txtName=txtName;
        this.txtReviewDetail=txtReviewDetail;
        this.reviewValue=reviewValue;
    }
    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtReviewDetail() {
        return txtReviewDetail;
    }

    public void setTxtReviewDetail(String txtReviewDetail) {
        this.txtReviewDetail = txtReviewDetail;
    }

    public float getReviewValue() {
        return reviewValue;
    }

    public void setReviewValue(float reviewValue) {
        this.reviewValue = reviewValue;
    }
}
