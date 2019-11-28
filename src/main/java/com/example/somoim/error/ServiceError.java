package com.example.somoim.error;

import com.example.somoim.type.ResultError;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public enum ServiceError {
    INTERNAL_DB_ERROR("40000","Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVCER_ERROR("50000","Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    ID_MIS_MATCH("40009","ID MIS MATCH", HttpStatus.CONFLICT),
    PWD_MIS_MATCH("40009","PWD MIS MATCH", HttpStatus.CONFLICT),
    SEND_MAIL_ERROR("50001","Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);


    public String resultCode;
    public String resultMessage;
    public HttpStatus httpStatus;

    ServiceError(String resultCode,String resultMessage, HttpStatus httpStatus) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ResultError getResultError(){
        ResultError resultError = ResultError.builder()
                .resultCode(getResultCode())
                .resultMessage(getResultMessage())
                .httpStatus(getHttpStatus())
                .build();
        return resultError;
    }

}
