package com.rustam.unitech.exception;

import com.rustam.unitech.enums.ResponseDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public class UniTechException extends RuntimeException {

    private  HttpStatus status;

    public UniTechException(ResponseDetails responseDetails) {
        super(responseDetails.getMessage());
        this.status = responseDetails.getHttpStatus();
    }

    public UniTechException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
