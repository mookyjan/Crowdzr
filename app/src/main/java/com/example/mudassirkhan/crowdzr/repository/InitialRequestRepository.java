package com.example.mudassirkhan.crowdzr.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mudassirkhan.crowdzr.api.CrowdZrServiceProvider;
import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;

import com.example.mudassirkhan.crowdzr.api.initialrequest.InitialRequestService;
import com.example.mudassirkhan.crowdzr.model.intiailRequest.InitialRequestResponse;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.util.UrlClass;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class InitialRequestRepository {

    private InitialRequestService mInitialRequestService;
    private static volatile InitialRequestRepository mInitialRequestRepository;

    private InitialRequestRepository() {
        mInitialRequestService = CrowdZrServiceProvider.get().initialRequest();
    }

    public static InitialRequestRepository get() {
        InitialRequestRepository instance = mInitialRequestRepository;
        if (instance == null) {
            synchronized (SignUpRepository.class) {
                instance = mInitialRequestRepository;
                if (instance == null) {
                    instance = mInitialRequestRepository = new InitialRequestRepository();
                }
            }
        }

        return instance;
    }

    public LiveData<InitialRequestResponse> initialRequest(String userId) {

        //final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        final MutableLiveData<InitialRequestResponse> data = new MutableLiveData<>();

        mInitialRequestService.getInitialRequest(UrlClass.getInitialUrl+userId).enqueue(new ErrorHandlingAdapter.MyCallback<InitialRequestResponse>() {
            @Override
            public void success(Response<InitialRequestResponse> response) {
                Log.d("initialRequest","intial request response "+response);
                data.postValue(response.body());
            }

            @Override
            public void failure(InitialRequestResponse response) {
                data.postValue(response);
            }
        });
        return data;
    }
}
