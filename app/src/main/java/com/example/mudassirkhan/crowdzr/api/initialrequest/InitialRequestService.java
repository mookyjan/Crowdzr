package com.example.mudassirkhan.crowdzr.api.initialrequest;

import com.example.mudassirkhan.crowdzr.api.eventmessages.ErrorHandlingAdapter;
import com.example.mudassirkhan.crowdzr.model.intiailRequest.InitialRequestResponse;

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface InitialRequestService {

    @GET()
    ErrorHandlingAdapter.MyCall<InitialRequestResponse> getInitialRequest(@Url String urlInitialRequest);
}
