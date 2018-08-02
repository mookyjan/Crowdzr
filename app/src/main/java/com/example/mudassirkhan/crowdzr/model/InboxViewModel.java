package com.example.mudassirkhan.crowdzr.model;

import android.widget.TextView;

public class InboxViewModel {

   private String  strUserName;
    private String strItemName;
    private String strTime;
    private String strItemDetail;

    public InboxViewModel(String strUserName,String strItemName,String strTime,String strItemDetail){
        this.strItemName=strItemName;
        this.strUserName=strUserName;
        this.strTime=strTime;
        this.strItemDetail=strItemDetail;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrItemName() {
        return strItemName;
    }

    public void setStrItemName(String strItemName) {
        this.strItemName = strItemName;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrItemDetail() {
        return strItemDetail;
    }

    public void setStrItemDetail(String strItemDetail) {
        this.strItemDetail = strItemDetail;
    }




}
