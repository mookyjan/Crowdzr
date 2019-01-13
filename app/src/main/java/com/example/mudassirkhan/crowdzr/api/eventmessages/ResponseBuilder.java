package com.example.mudassirkhan.crowdzr.api.eventmessages;

/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class ResponseBuilder {
    private static final ResponseBuilder instance = new ResponseBuilder(); // singleton instance
    private static Class<?> clazz;
    private ResponseBuilder() { } // private constructor for singleton pattern
    // singleton 'of' method.
    public static ResponseBuilder of(Class<?> classType) {
        clazz = classType;
        return instance; }

    public <T extends BaseResponse> T build() {
        try {
            T response = (T) clazz.newInstance();
            response.setNetworkError(this.isNetworkError);
            response.setErrorMessage(this.errorMessage);
            response.setStatus(this.status);
            response.setStatusCode(this.statusCode);
            return (T) clazz.cast(response);
        }
        catch(Exception e) { throw new RuntimeException("Failed to create response instance"); }
    }

    private Integer status;
    private String errorMessage = "";
    private boolean isNetworkError;

    public ResponseBuilder setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public ResponseBuilder setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
    public ResponseBuilder setNetworkError(boolean isNetworkError) {
        this.isNetworkError = isNetworkError;
        return this;
    }

    private StatusCode statusCode = StatusCode.SUCCESS;

    public ResponseBuilder setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }


//    private static final String STATUS = "status";
//    private static final String ERROR_MESSAGE = "errorMessage";
//    private static final String NETWORK_ERROR = "networkError";
//    private Integer errorCode;
//    private String errorMessage = "";
//    private boolean isNetworkError;
//
//    public ResponseBuilder<?> setStatus(Integer status) {
//        this.errorCode = status;
//        return this;
//    }
//
//    public ResponseBuilder<?> setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//        return this;
//    }
//    public ResponseBuilder<?> setNetworkError(boolean isNetowrkError) {
//        this.isNetworkError = isNetowrkError;
//        return this;
//    }
//
//    private final Class<?> clazz;
//
//    public ResponseBuilder(Class<T> clazz) {
//        super();
//        this.clazz = clazz;
//    }
//
//    public static ResponseBuilder<?> of(Class<?> clazz) {
//        return new ResponseBuilder<>(clazz);
//    }
//
//    private ResponseBuilder<T> setProperty(T instance, String name, Object value)
//            throws IllegalAccessException,
//            IllegalArgumentException,
//            InvocationTargetException,
//            NoSuchMethodException,
//            SecurityException {
//        try {
//            if (value != null) {
//                invoke(instance, name, value, value.getClass());
//            } else {
//                findMethodAndInvoke(instance, name, value);
//            }
//        } catch (NoSuchMethodException nm) {
//            if (value.getClass() == java.lang.Integer.class) {
//                invoke(instance, name, value, int.class);
//            } else if (value.getClass() == java.lang.Long.class) {
//                invoke(instance, name, value, long.class);
//            } else if (value.getClass() == java.lang.Float.class) {
//                invoke(instance, name, value, float.class);
//            } else if (value.getClass() == java.lang.Double.class) {
//                invoke(instance, name, value, double.class);
//            } else if (value.getClass() == java.lang.Boolean.class) {
//                invoke(instance, name, value, boolean.class);
//            } else {
//                findMethodAndInvoke(instance, name, value);
//            }
//        }
//        return this;
//    }
//
//    private void findMethodAndInvoke(T instance, String name, Object value)
//            throws IllegalAccessException,
//            InvocationTargetException,
//            NoSuchMethodError {
//        Method[] methods = clazz.getMethods();
//        String setterName = getSetterName(name);
//        boolean invoked = false;
//        for (int i = 0; i < methods.length; i++) {
//            Method method = methods[i];
//            if (method.getName().equals(setterName)) {
//                method.invoke(instance, value);
//                invoked = true;
//            }
//        }
//        if (!invoked) {
//            throw new NoSuchMethodError(
//                    "Cannot find method with name " + setterName);
//        }
//    }
//
//    private String getSetterName(String name) {
//        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
//    }
//
//    private void invoke(T instance, String name, Object value, Class<?> claz)
//            throws NoSuchMethodException,
//            SecurityException,
//            IllegalAccessException,
//            IllegalArgumentException,
//            InvocationTargetException {
//        Method method = clazz.getMethod(getSetterName(name), claz);
//        method.invoke(instance, value);
//    }
//
//    public T build() {
//        @SuppressWarnings("unchecked")
//
//        T instance = null;
//        try {
//            instance = (T) clazz.newInstance();
//            setProperty(instance, STATUS, this.errorCode);
//            setProperty(instance, ERROR_MESSAGE, this.errorMessage);
//            setProperty(instance, NETWORK_ERROR, this.isNetworkError);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        catch (Exception e) {
//            throw new RuntimeException("Unable to set value with builder", e);
//        }
//        return instance;
//    }
}
