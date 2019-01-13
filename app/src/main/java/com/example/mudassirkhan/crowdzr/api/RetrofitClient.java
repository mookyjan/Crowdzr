package com.example.mudassirkhan.crowdzr.api;


import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;
import com.example.mudassirkhan.crowdzr.api.prefs.AccessTokenPrefs;
import com.example.mudassirkhan.crowdzr.api.prefs.AppPreferencesHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mudassir khan on 31/10/2018.
 */

public class RetrofitClient {


//    public static final String BASE_URL = "http://172.16.13.208:3000/";
//      public static final String BASE_URL = "http://ec2-35-177-120-23.eu-west-2.compute.amazonaws.com/";
      public static final String BASE_URL = "https://m4y9z9b6s9.execute-api.us-east-1.amazonaws.com/";


    private static volatile Retrofit retrofit;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        Retrofit instance = retrofit;
        if (instance == null) {
            synchronized (RetrofitClient.class) {

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                                AppPreferencesHelper.get().getAccessToken());

                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
//                        return chain.proceed(originalRequest);
                    }
                }).build();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();
                instance = retrofit;
                if (instance == null) {
                    instance = retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                           // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addCallAdapterFactory(new ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                }
            }
        }

        return instance;
    }
}
