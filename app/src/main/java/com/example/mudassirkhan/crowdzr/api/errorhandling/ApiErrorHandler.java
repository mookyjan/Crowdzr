package com.example.mudassirkhan.crowdzr.api.errorhandling;

public class ApiErrorHandler<T> {


    /* Method should be overided to return custom exception type which
     *  would be a sub-type of Exception
     */
    public Exception getExceptionType( retrofit2.Response<T> response ) {
        return new Exception();
    }

}
