package com.example.mudassirkhan.crowdzr.model.managesales;

public class ResponseOrderDetail {


    private String strDate,strTime,strDetail,strLocation;

    public ResponseOrderDetail(String strDate,String strTime,String strDetail,String strLocation){
        this.strDate=strDate;
        this.strTime=strTime;
        this.strDetail=strDetail;
        this.strLocation=strLocation;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrDetail() {
        return strDetail;
    }

    public void setStrDetail(String strDetail) {
        this.strDetail = strDetail;
    }

    public String getStrLocation() {
        return strLocation;
    }

    public void setStrLocation(String strLocation) {
        this.strLocation = strLocation;
    }
}
