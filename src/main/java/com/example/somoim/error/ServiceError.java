package com.example.somoim.error;

import com.example.somoim.type.ResultError;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public enum ServiceError {
    INTERNAL_DB_ERROR(){
        @Override
        public ResultError getResultError() {
            String message = getMessage();
            return ResultError.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .resultCode("40000")
                    .resultMessage(StringUtils.isEmpty(message) ? "INTERNAL_DB_ERROR" : message)
                    .build();
        }
    },

    INTERNAL_SERVCER_ERROR(){
        @Override
        public ResultError getResultError() {
            String message = getMessage();
            return ResultError.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .resultCode("50000")
                    .resultMessage(StringUtils.isEmpty(message) ? "INTERNAL_SERVER_ERROR" : message)
                    .build();
        }
    }
    ;

    private String message;

    public abstract ResultError getResultError();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
