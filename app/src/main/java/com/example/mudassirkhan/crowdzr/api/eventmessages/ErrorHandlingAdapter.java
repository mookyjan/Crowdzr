package com.example.mudassirkhan.crowdzr.api.eventmessages;

import com.example.mudassirkhan.crowdzr.api.RetrofitClient;
import com.example.mudassirkhan.crowdzr.util.ConnectivityUtils;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.mudassirkhan.crowdzr.api.eventmessages.BaseResponse.FAILURE_STATUS;


/**
 * Created by muhammadrashid on 12/01/2018.
 */

public final class ErrorHandlingAdapter {
    /** A callback which offers granular callbacks for various conditions. */
   public interface MyCallback<T> {
        /** Called for [200, 300) responses. */
        void success(Response<T> response);
        /** Called for 401 responses. */
        //void unauthenticated(Response<?> response);
        /** Called for [400, 500) responses, except 401. */
       // void clientError(Response<?> response);
        /** Called for [500, 600) response. */
       // void serverError(Response<?> response);
        /** Called for network errors while making the call. */
       // void networkError(IOException e);
        /** Called for unexpected errors while making the call. */
       // void unexpectedError(Throwable t);
        /** Called for failure while making the call.
         * @param response*/
        void failure(T response);
    }

   public interface MyCall<T> {
        void cancel();
        void enqueue(MyCallback<T> callback);
        MyCall<T> clone();

        // Left as an exercise for the reader...
        // TODO MyResponse<T> execute() throws MyHttpException;
    }

    public static class ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
        @Override
        public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations,
                                     Retrofit retrofit) {
            if (getRawType(returnType) != MyCall.class) {
                return null;
            }
            if (!(returnType instanceof ParameterizedType)) {
                throw new IllegalStateException(
                        "MyCall must have generic type (e.g., MyCall<ResponseBody>)");
            }
            Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
            Executor callbackExecutor = retrofit.callbackExecutor();
            return new ErrorHandlingCallAdapter<>(responseType, callbackExecutor);
        }

        private static final class ErrorHandlingCallAdapter<R> implements CallAdapter<R, MyCall<R>> {
            private final Type responseType;
            private final Executor callbackExecutor;

            ErrorHandlingCallAdapter(Type responseType, Executor callbackExecutor) {
                this.responseType = responseType;
                this.callbackExecutor = callbackExecutor;
            }

            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public MyCall<R> adapt(Call<R> call) {
                return new MyCallAdapter<>(call, callbackExecutor);
            }
        }
    }

    /** Adapts a {@link Call} to {@link MyCall}. */
   public static class MyCallAdapter<T> implements MyCall<T> {
        private static final String TAG = MyCallAdapter.class.getSimpleName();
        private final Call<T> call;
        private final Executor callbackExecutor;
        private int totalRetries = 3;
        private int retryCount = 0;

        MyCallAdapter(Call<T> call, Executor callbackExecutor) {
            this.call = call;
            this.callbackExecutor = callbackExecutor;
        }

        @Override
        public void cancel() {
            call.cancel();
        }

        @SuppressWarnings("unchecked")
        @Override
        public void enqueue(final MyCallback<T> callback) {

            final Class<T> clazz = (Class<T>) ((ParameterizedType)
                    callback.getClass().getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];
            if(!ConnectivityUtils.isConnected()){
                //callback.networkError(new IOException("Network error"));

                callback.failure((T) ResponseBuilder
                        .of(clazz)
                        .setStatus(FAILURE_STATUS)
                        .setStatusCode(StatusCode.NETWORK_ERROR)
                        .setErrorMessage("Network error")
                        .build());
                return;
            }

            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                    // on that executor by submitting a Runnable. This is left as an exercise for the reader.

                    int code = response.code();
                    BaseResponse baseResponse = null;

                    if (code >= 200 && code < 300) {
                        callback.success(response);
                    } else {
                        String message = response.message();
                        if (response.errorBody() != null) {
                            Converter<ResponseBody, T> errorConverter =
                                    RetrofitClient.getClient().responseBodyConverter(clazz, new Annotation[0]);
                            try {
                                baseResponse = (BaseResponse) errorConverter.convert(response.errorBody());
                                if (baseResponse.getErrorMessage() != null) {
                                    message = baseResponse.getErrorMessage().toString();
                                }
                            } catch (IOException e) {
                                //Ignore this exception
                            }
                        }

                        if (code == 401) {
                            if (baseResponse != null) {
                                baseResponse.setStatusCode(StatusCode.UN_AUTHENTICATED);
                                callback.failure((T) baseResponse);
                            }
                            else{
                                callback.failure((T)ResponseBuilder
                                        .of(clazz)
                                        .setStatus(FAILURE_STATUS)
                                        .setStatusCode(StatusCode.UN_AUTHENTICATED)
                                        .setErrorMessage(message)
                                        .build());
                            }
                        } else if (code >= 400 && code < 500) {
                            if (baseResponse != null) {
                                baseResponse.setStatusCode(StatusCode.CLIENT_ERROR);
                                callback.failure((T) baseResponse);
                            }
                            else{
                                callback.failure((T)ResponseBuilder
                                        .of(clazz)
                                        .setStatus(FAILURE_STATUS)
                                        .setStatusCode(StatusCode.CLIENT_ERROR)
                                        .setErrorMessage(message)
                                        .build());
                            }

                        } else if (code >= 500 && code < 600) {
                            if (retryCount++ < totalRetries) call.clone().enqueue(this);
                            else {
                                if (baseResponse != null) {
                                    baseResponse.setStatusCode(StatusCode.SERVER_ERROR);
                                    callback.failure((T) baseResponse);
                                }
                                else{
                                    callback.failure((T)ResponseBuilder
                                            .of(clazz)
                                            .setStatus(FAILURE_STATUS)
                                            .setStatusCode(StatusCode.SERVER_ERROR)
                                            .setErrorMessage(message)
                                            .build());
                                }
                                retryCount = 0;
                            }
                        } else {
                            if (retryCount++ < totalRetries) call.clone().enqueue(this);
                            else {
                                if (baseResponse != null) {
                                    baseResponse.setStatusCode(StatusCode.UNEXPECTED_ERROR);
                                    callback.failure((T) baseResponse);
                                }
                                else{
                                    callback.failure((T)ResponseBuilder
                                            .of(clazz)
                                            .setStatus(FAILURE_STATUS)
                                            .setStatusCode(StatusCode.UNEXPECTED_ERROR)
                                            .setErrorMessage(message)
                                            .build());
                                }
                                retryCount = 0;
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                    // on that executor by submitting a Runnable. This is left as an exercise for the reader.

                    if (t instanceof IOException) {
                        callback.failure((T) ResponseBuilder
                                .of(clazz)
                                .setStatus(FAILURE_STATUS)
                                .setStatusCode(StatusCode.NETWORK_ERROR)
                                .setErrorMessage(t.getMessage())
                                .build());
                    } else {
                        if (retryCount++ < totalRetries) call.clone().enqueue(this);
                        else {
                            callback.failure((T) ResponseBuilder
                                    .of(clazz)
                                    .setStatus(FAILURE_STATUS)
                                    .setStatusCode(StatusCode.UNEXPECTED_ERROR)
                                    .setErrorMessage(t.getMessage())
                                    .build());

                            retryCount = 0;
                        }
                    }
                }
            });
        }

        @Override
        public MyCall<T> clone() {
            return new MyCallAdapter<>(call.clone(), callbackExecutor);
        }
    }
}
