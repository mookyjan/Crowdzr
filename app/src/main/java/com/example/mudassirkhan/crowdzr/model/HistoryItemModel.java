package com.example.mudassirkhan.crowdzr.model;

public class HistoryItemModel {

    String txtTitle,txtDate,txtStatus,txtPrice;
    public HistoryItemModel(String txtTitle,String txtDate,String txtStatus,String txtPrice){
        this.txtTitle=txtTitle;
        this.txtDate=txtDate;
        this.txtStatus=txtStatus;
        this.txtPrice=txtPrice;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }

    public String getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(String txtStatus) {
        this.txtStatus = txtStatus;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }
}
