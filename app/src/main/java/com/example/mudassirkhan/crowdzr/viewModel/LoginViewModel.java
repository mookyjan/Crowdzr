package com.example.mudassirkhan.crowdzr.viewModel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;


import com.example.mudassirkhan.crowdzr.CrowdzrApp;
import com.example.mudassirkhan.crowdzr.api.eventmessages.NetworkErrorLiveEvent;
import com.example.mudassirkhan.crowdzr.api.eventmessages.login.ValidationLiveEvent;
import com.example.mudassirkhan.crowdzr.api.prefs.AccessTokenPrefs;
import com.example.mudassirkhan.crowdzr.api.prefs.UserPrefs;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.repository.LoginRepository;
import com.google.gson.JsonObject;

public class LoginViewModel extends AndroidViewModel {

    private LiveData<LoginResponse> mObservableLoginResponse=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    private final ValidationLiveEvent mObservableResponseMessage = new ValidationLiveEvent();

    public ValidationLiveEvent getObservableResponseMessage() {
        return mObservableResponseMessage;
    }

    private final NetworkErrorLiveEvent mObservableNetworkMessage = new NetworkErrorLiveEvent();

    public NetworkErrorLiveEvent getObservableNetworkMessage() {
        return mObservableNetworkMessage;
    }


    public LiveData<LoginResponse> login(JsonObject jsonObject){
        LiveData<LoginResponse> trigger= LoginRepository.get().login(jsonObject);
        LiveData<LoginResponse> response= Transformations.switchMap(trigger, new Function<LoginResponse, LiveData<LoginResponse>>() {
            @Override
            public LiveData<LoginResponse> apply(LoginResponse signUpResponse) {
                if (signUpResponse!=null){
                    if (signUpResponse.isSuccess()){
//updateTokenId();
                        ((MutableLiveData<LoginResponse>) mObservableLoginResponse).setValue(signUpResponse);
                    }else {
                        mObservableNetworkMessage.setValue(signUpResponse);
                    }

                }
                else {
                    ((MutableLiveData<LoginResponse>) mObservableLoginResponse).setValue(signUpResponse);
                }
                return mObservableLoginResponse;

            }
        });
        return response;

    }

    private void updateTokenId(int newUserId){
        int oldUserId = UserPrefs.get(CrowdzrApp.getsContext()).getUserId();
        if (oldUserId != newUserId) {
            AccessTokenPrefs.get().removeAccessToken();
            UserPrefs.get(CrowdzrApp.getsContext()).setUserId(newUserId);
        }
    }
}
