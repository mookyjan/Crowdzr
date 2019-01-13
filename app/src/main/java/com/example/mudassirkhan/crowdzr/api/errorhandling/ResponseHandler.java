package com.example.mudassirkhan.crowdzr.api.errorhandling;

import android.support.annotation.NonNull;

public interface ResponseHandler<T> {
    void onFinish(@NonNull T data);
    void onError(retrofit2.Response<T> response);
    void onException(Exception exception);
}
