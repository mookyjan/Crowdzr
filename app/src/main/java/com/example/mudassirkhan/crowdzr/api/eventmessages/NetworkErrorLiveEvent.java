/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.mudassirkhan.crowdzr.api.eventmessages;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.mudassirkhan.crowdzr.CrowdzrApp;
import com.example.mudassirkhan.crowdzr.R;


/**
 * A SingleLiveEvent used for prompt messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class NetworkErrorLiveEvent<T extends BaseResponse> extends SingleLiveEvent<BaseResponse> {

    public void observe(final LifecycleOwner owner, final NetworkErrorObserver observer) {
        super.observe(owner, new Observer<BaseResponse>() {
            @Override
            public void onChanged(@Nullable BaseResponse t) {
                if (t == null) {
                    return;
                }
               handleNetworkError(t.getStatusCode(),t.getErrorMessage().toString(),observer);
            }
        });
    }

    public interface NetworkErrorObserver<String> {
        void onNetworkError(String title, String message);
        void onUnauthenticatedError(String title, String message);
        void onClientError(String title, String message);
        void onUnexpectedError(String title, String message);
    }

    public void handleNetworkError(StatusCode code, String message, @NonNull NetworkErrorObserver observer) {
        Context context = CrowdzrApp.getsContext();
        switch (code) {
            case NETWORK_ERROR:
                message = context.getResources().getString(R.string.network_fail_message);
                observer.onNetworkError(message, message);
                break;
            case UN_AUTHENTICATED:
                if(TextUtils.isEmpty(message)) {
                    message = context.getResources().getString(R.string.unauthenticated_error);
                }
                observer.onUnauthenticatedError(message, message);
                break;
            case SUCCESS:
                break;
            case CLIENT_ERROR:
                if(TextUtils.isEmpty(message)) {
                    message = context.getResources().getString(R.string.client_error);
                }
                observer.onClientError(message, message);
                break;
            case UNEXPECTED_ERROR:
            case SERVER_ERROR:
                if(TextUtils.isEmpty(message)) {
                    message = context.getResources().getString(R.string.server_error);
                }
                observer.onUnexpectedError(message, message);
                break;
        }
    }
}
