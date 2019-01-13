package com.example.mudassirkhan.crowdzr.viewModel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.mudassirkhan.crowdzr.api.eventmessages.NetworkErrorLiveEvent;
import com.example.mudassirkhan.crowdzr.api.eventmessages.login.ValidationLiveEvent;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.model.request.PostRequestResponse;
import com.example.mudassirkhan.crowdzr.repository.LoginRepository;
import com.example.mudassirkhan.crowdzr.repository.PostRequestRepository;
import com.google.gson.JsonObject;

public class PostRequestViewModel extends AndroidViewModel {

    private MutableLiveData<PostRequestResponse> mObservablePostRequestResponse = new MutableLiveData<>();
   private LiveData<PostRequestResponse> mPostRequestResponseLiveData=new MutableLiveData<>();
    private final ValidationLiveEvent mObservableResponseMessage = new ValidationLiveEvent();

    public ValidationLiveEvent getObservableResponseMessage() {
        return mObservableResponseMessage;
    }

    private final NetworkErrorLiveEvent mObservableNetworkMessage = new NetworkErrorLiveEvent();

    public NetworkErrorLiveEvent getObservableNetworkMessage() {
        return mObservableNetworkMessage;
    }
    public PostRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PostRequestResponse> getPostRequestInfo(JsonObject jsonObjectPostRequest){
//        if (mPostRequestResponseLiveData.getValue() == null || mPostRequestResponseLiveData.getValue().isNetworkError()) {
            mPostRequestResponseLiveData = (MutableLiveData<PostRequestResponse>) PostRequestRepository.get().postRequest(jsonObjectPostRequest);
//        } else {
//            return;
//        }
        return mPostRequestResponseLiveData;
    }

    public LiveData<PostRequestResponse> getObservablePostRequestResponse() {

        return mPostRequestResponseLiveData;
    }


    public LiveData<PostRequestResponse> postRequest(JsonObject jsonObject){
        LiveData<PostRequestResponse> trigger= PostRequestRepository.get().postRequest(jsonObject);
        LiveData<PostRequestResponse> response= Transformations.switchMap(trigger, new Function<PostRequestResponse, LiveData<PostRequestResponse>>() {
            @Override
            public LiveData<PostRequestResponse> apply(PostRequestResponse postRequestResponse) {
                if (postRequestResponse!=null){
                    if (postRequestResponse.isSuccess()){
//updateTokenId();
                        ((MutableLiveData<PostRequestResponse>) mObservablePostRequestResponse).setValue(postRequestResponse);
                    }else {
                        mObservableNetworkMessage.setValue(postRequestResponse);
                    }

                }
                else {
                    ((MutableLiveData<PostRequestResponse>) mObservablePostRequestResponse).setValue(postRequestResponse);
                }
                return mObservablePostRequestResponse;

            }
        });
        return response;

    }



}
