package com.example.mudassirkhan.crowdzr.api.signup;

import com.example.mudassirkhan.crowdzr.model.signup.SignupModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface SignupService {
//    "stg/users/signup"
    @POST()
    Call<SignupModel> signup(@Url String singupUrl, @Body JsonObject jsonObject);
}
