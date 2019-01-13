package com.example.mudassirkhan.crowdzr.api.request;

import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;
import com.example.mudassirkhan.crowdzr.model.request.PostRequestResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface PostRequestService {

    @POST
    Call<PostRequestResponse> postRequest(@Url String postRequestUrl, @Body JsonObject jsonObject);

}
