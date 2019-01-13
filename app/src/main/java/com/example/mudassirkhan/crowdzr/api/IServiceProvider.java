package com.example.mudassirkhan.crowdzr.api;

import com.example.mudassirkhan.crowdzr.api.initialrequest.InitialRequestService;
import com.example.mudassirkhan.crowdzr.api.login.LoginService;
import com.example.mudassirkhan.crowdzr.api.request.PostRequestService;
import com.example.mudassirkhan.crowdzr.api.signup.SignupService;

public interface IServiceProvider {

    SignupService signupService();

    LoginService loginService();

    PostRequestService postRequest();

    InitialRequestService initialRequest();
}
