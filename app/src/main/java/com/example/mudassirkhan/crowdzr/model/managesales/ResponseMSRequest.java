package com.example.mudassirkhan.crowdzr.model.managesales;

public class ResponseMSRequest {

    private String txtUserName,txtItemName,txtDate,txtPrice;

    public ResponseMSRequest(String txtUserName,String txtItemName,String txtDate,String txtPrice){
        this.txtUserName=txtUserName;
        this.txtItemName=txtItemName;
        this.txtDate=txtDate;
        this.txtPrice=txtPrice;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getTxtItemName() {
        return txtItemName;
    }

    public void setTxtItemName(String txtItemName) {
        this.txtItemName = txtItemName;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    int imageProfile;

}
