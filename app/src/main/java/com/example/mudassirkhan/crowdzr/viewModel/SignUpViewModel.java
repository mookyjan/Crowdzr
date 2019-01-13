package com.example.mudassirkhan.crowdzr.viewModel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.mudassirkhan.crowdzr.model.signup.SignupModel;
import com.example.mudassirkhan.crowdzr.repository.SignUpRepository;
import com.google.gson.JsonObject;

public class SignUpViewModel extends AndroidViewModel {
    private LiveData<SignupModel> mObservableSignUpResponse=new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<SignupModel> signUp(JsonObject jsonObject){
        LiveData<SignupModel> trigger= SignUpRepository.get().signUp(jsonObject);
        LiveData<SignupModel> response= Transformations.switchMap(trigger, new Function<SignupModel, LiveData<SignupModel>>() {
            @Override
            public LiveData<SignupModel> apply(SignupModel signUpResponse) {
                if (signUpResponse!=null){
                    updateUserId(signUpResponse.getToken());
                    ((MutableLiveData<SignupModel>) mObservableSignUpResponse).setValue(signUpResponse);
                }
                else {
                    ((MutableLiveData<SignupModel>) mObservableSignUpResponse).setValue(signUpResponse);
                }
                return mObservableSignUpResponse;

            }
        });
        return response;

    }

    private void updateUserId(String  userToken){
//        int oldUserId = UserPrefs.get(BoltonApp.getsContext()).getUserId();
//        if (oldUserId != newUserId) {
//            AccessTokenPrefs.get().removeAccessToken();
//            UserPrefs.get(BoltonApp.getsContext()).setUserId(newUserId);
//        }
    }
}
