package com.example.mudassirkhan.crowdzr.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mudassirkhan.crowdzr.CrowdzrApp;
import com.example.mudassirkhan.crowdzr.api.CrowdZrServiceProvider;
import com.example.mudassirkhan.crowdzr.api.eventmessages.ResponseBuilder;
import com.example.mudassirkhan.crowdzr.api.request.PostRequestService;
import com.example.mudassirkhan.crowdzr.model.request.PostRequestResponse;
import com.example.mudassirkhan.crowdzr.util.UrlClass;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mudassirkhan.crowdzr.api.eventmessages.BaseResponse.FAILURE_STATUS;

public class PostRequestRepository {
    private static volatile PostRequestRepository mPostRequestRepository;
    private PostRequestService mPostRequestService;

    private PostRequestRepository() {
        mPostRequestService = CrowdZrServiceProvider.get().postRequest();
    }

    public static PostRequestRepository get() {
        PostRequestRepository instance = mPostRequestRepository;
        if (instance == null) {
            synchronized (PostRequestRepository.class) {
                instance = mPostRequestRepository;
                if (instance == null) {
                    instance = mPostRequestRepository = new PostRequestRepository();
                }
            }
        }

        return instance;
    }

    public LiveData<PostRequestResponse> postRequest(JsonObject jsonObjectPostRequest) {

        final MutableLiveData<PostRequestResponse> data = new MutableLiveData<>();

        mPostRequestService.postRequest(UrlClass.postRequestUrl,jsonObjectPostRequest).enqueue(new Callback<PostRequestResponse>() {
            @Override
            public void onResponse(Call<PostRequestResponse> call, Response<PostRequestResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PostRequestResponse> call, Throwable t) {
                Log.d("Error", "ErrorPost request");
                data.setValue(
                        ResponseBuilder
                                .of(PostRequestResponse.class)
                                .setStatus(FAILURE_STATUS)
                                .setErrorMessage(t.getMessage())
                                .setNetworkError(t instanceof IOException ? true : false)
                                .<PostRequestResponse>build());
            }
        });

        return data;
    }





}
