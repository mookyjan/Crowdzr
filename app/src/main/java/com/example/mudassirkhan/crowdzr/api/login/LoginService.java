package com.example.mudassirkhan.crowdzr.api.login;

import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LoginService {
//    "stg/users/signin"

    @POST()
    ErrorHandlingAdapter.MyCall<LoginResponse> login(@Url String loginUrl, @Body JsonObject jsonObject);
}
