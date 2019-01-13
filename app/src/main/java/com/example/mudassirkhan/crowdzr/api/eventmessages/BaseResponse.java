package com.example.mudassirkhan.crowdzr.api.eventmessages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muhammadrashid on 29/12/2017.
 */

public class BaseResponse {

    public static final int DEFAULT_ERROR_CODE = 50;
    public static final int FAILURE_STATUS = 0;
    public static final int SUCCESS_STATUS = 1;

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

    public boolean isNetworkError() {
        return isNetworkError;
    }

    public void setNetworkError(boolean networkError) {
        isNetworkError = networkError;
    }

    private boolean isNetworkError;

    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    private StatusCode statusCode;

    public boolean isSuccess(){
       return this.errorMessage == null;
    }

    public boolean isError(){
        return this.errorMessage != null;
    }

    public boolean isApiError(){
        return this.statusCode != StatusCode.SUCCESS;
    }

    public BaseResponse getResponse(){
        return this;
    }
}



