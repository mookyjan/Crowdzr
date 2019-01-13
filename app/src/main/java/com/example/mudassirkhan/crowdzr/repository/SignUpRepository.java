package com.example.mudassirkhan.crowdzr.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mudassirkhan.crowdzr.api.CrowdZrServiceProvider;
import com.example.mudassirkhan.crowdzr.api.signup.SignupService;
import com.example.mudassirkhan.crowdzr.model.signup.SignupModel;
import com.example.mudassirkhan.crowdzr.util.UrlClass;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {

    private SignupService mSignupService;
    private static volatile SignUpRepository mSignupRepository;

    private SignUpRepository() {
        mSignupService = CrowdZrServiceProvider.get().signupService();
    }

    public static SignUpRepository get() {
        SignUpRepository instance = mSignupRepository;
        if (instance == null) {
            synchronized (SignUpRepository.class) {
                instance = mSignupRepository;
                if (instance == null) {
                    instance = mSignupRepository = new SignUpRepository();
                }
            }
        }

        return instance;
    }

    public LiveData<SignupModel> signUp(JsonObject signupJsonObject) {

        //final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        final MutableLiveData<SignupModel> data = new MutableLiveData<>();
        mSignupService.signup(UrlClass.signUpUrl,signupJsonObject).enqueue(new Callback<SignupModel>() {
            @Override
            public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {
                Log.d("SignUp","signup response "+response);
                if (response.isSuccessful()){
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignupModel> call, Throwable t) {
             Log.d("SignUp","signup failure");
             if (t!=null){
                 t.printStackTrace();
             }
            }
        });



        return data;
    }
}
