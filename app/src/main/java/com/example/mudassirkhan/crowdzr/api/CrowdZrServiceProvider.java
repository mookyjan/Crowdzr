package com.example.mudassirkhan.crowdzr.api;

import com.example.mudassirkhan.crowdzr.api.initialrequest.InitialRequestService;
import com.example.mudassirkhan.crowdzr.api.login.LoginService;
import com.example.mudassirkhan.crowdzr.api.request.PostRequestService;
import com.example.mudassirkhan.crowdzr.api.signup.SignupService;

public class CrowdZrServiceProvider implements IServiceProvider{
    private static volatile CrowdZrServiceProvider retrofitService;

    public static CrowdZrServiceProvider get() {
        if (retrofitService == null) {
            synchronized (CrowdZrServiceProvider.class) {
                if (retrofitService == null) {
                    retrofitService = new CrowdZrServiceProvider();
                }
            }
        }
        return retrofitService;
    }

    private CrowdZrServiceProvider() {
    }

    @Override
    public SignupService signupService() {
        return RetrofitClient.getClient().create(SignupService.class);
    }

    @Override
    public LoginService loginService() {
        return RetrofitClient.getClient().create(LoginService.class);
    }

    @Override
    public PostRequestService postRequest() {
        return RetrofitClient.getClient().create(PostRequestService.class);
    }

    @Override
    public InitialRequestService initialRequest() {
        return RetrofitClient.getClient().create(InitialRequestService.class);
    }
}
