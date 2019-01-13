package com.example.mudassirkhan.crowdzr.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mudassirkhan.crowdzr.api.CrowdZrServiceProvider;
import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;
import com.example.mudassirkhan.crowdzr.api.login.LoginService;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.model.signup.SignupModel;
import com.example.mudassirkhan.crowdzr.util.UrlClass;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private LoginService mLoginService;
    private static volatile LoginRepository mLoginRepository;

    private LoginRepository() {
        mLoginService = CrowdZrServiceProvider.get().loginService();
    }

    public static LoginRepository get() {
        LoginRepository instance = mLoginRepository;
        if (instance == null) {
            synchronized (SignUpRepository.class) {
                instance = mLoginRepository;
                if (instance == null) {
                    instance = mLoginRepository = new LoginRepository();
                }
            }
        }

        return instance;
    }

    public LiveData<LoginResponse> login(JsonObject loginJsonObject) {

        //final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();

        mLoginService.login(UrlClass.loginUrl,loginJsonObject).enqueue(new ErrorHandlingAdapter.MyCallback<LoginResponse>() {
            @Override
            public void success(Response<LoginResponse> response) {
                Log.d("SignIn","signIn response "+response);
                data.postValue(response.body());
            }

            @Override
            public void failure(LoginResponse response) {
              data.postValue(response);
            }
        });
//        mLoginService.login(UrlClass.loginUrl,loginJsonObject).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Log.d("SignIn","signIn response "+response);
//                if (response.isSuccessful()){
//                    data.postValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Log.d("SignIn","signIn failure");
//                if (t!=null){
//                    t.printStackTrace();
//                }
//            }
//        });

        return data;
    }
}
