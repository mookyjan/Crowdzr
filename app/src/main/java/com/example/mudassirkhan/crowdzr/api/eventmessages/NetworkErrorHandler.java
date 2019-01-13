package com.example.mudassirkhan.crowdzr.api.eventmessages;

import android.content.Context;

import com.example.mudassirkhan.crowdzr.R;


/**
 * Created by muhammadrashid on 22/02/2018.
 */

public class NetworkErrorHandler {
    public static void handleNetworkError(Context context, StatusCode code, String message, NetworkErrorLiveEvent event) {
        switch (code) {
            case NETWORK_ERROR:
                event.setValue(
                        new EventMessage.Builder()
                                .withMessage(context.getResources().getString(R.string.network_fail_message))
                                .build());
                break;
            case UN_AUTHENTICATED:
                event.setValue(
                        new EventMessage.Builder()
                                .withMessage(context.getResources().getString(R.string.unauthenticated_error))
                                .build());
                break;
            case CLIENT_ERROR:
                event.setValue(
                        new EventMessage.Builder()
                                .withMessage(context.getResources().getString(R.string.client_error))
                                .build());
                break;
            case UNEXPECTED_ERROR:
            case SERVER_ERROR:
                event.setValue(
                        new EventMessage.Builder()
                                .withMessage(context.getResources().getString(R.string.server_error))
                                .build());
                break;
        }
    }
}
