package com.example.somoim.error;

public class ServiceException extends RuntimeException {
    private final ServiceError serviceError;

    public ServiceException(ServiceError serviceError) {
        this.serviceError = serviceError;
    }

    public ServiceException(String message, ServiceError serviceError) {
        super(message);
        this.serviceError = serviceError;
    }

    public ServiceException(String message, Throwable cause, ServiceError serviceError) {
        super(message, cause);
        this.serviceError = serviceError;
    }

    public ServiceException(Throwable cause, ServiceError serviceError) {
        super(cause);
        this.serviceError = serviceError;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ServiceError serviceError) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.serviceError = serviceError;
    }
}
