package com.rustam.unitech.enums;

import org.springframework.http.HttpStatus;

public enum ResponseDetails {

    ACCOUNT_CREATED("Account successfully created.", HttpStatus.CREATED),
    TRANSACTION_COMPLETED("Transaction was successfully completed.", HttpStatus.OK),
    ACCOUNT_NOT_FOUND("Account not found.", HttpStatus.NOT_FOUND),
    ACCOUNT_ALREADY_EXIST("Account already exist.", HttpStatus.BAD_REQUEST),

    TRANSFER_TO_SAME_ACCOUNT("Attempt to transfer money on the same account.", HttpStatus.BAD_REQUEST),
    TRANSFER_FROM_INACTIVE_ACCOUNT("Attempt to transfer money from an inactive account.", HttpStatus.BAD_REQUEST),
    TRANSFER_TO_INACTIVE_ACCOUNT("Attempt to transfer the money to an inactive account.", HttpStatus.BAD_REQUEST),
    TRANSFER_TO_NON_EXISTING_ACCOUNT("Attempt to transfer the money to non existing account.", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND("User has not been found by provided email.", HttpStatus.NOT_FOUND),
    USER_REGISTERED("User has been successfully registered.", HttpStatus.CREATED),
    USERNAME_EXIST("Username is already exist.", HttpStatus.BAD_REQUEST),
    NOT_AUTHORIZED_REQUEST("User has not been authorized.", HttpStatus.BAD_REQUEST),

    USER_PASSWORD_WRONG("User password is wrong.", HttpStatus.BAD_REQUEST),



    JWT_TIME_EXPIRED("Jwt token time expired.", HttpStatus.BAD_REQUEST),
    JWT_UNTRUSTED("Jwt token is untrusted.", HttpStatus.BAD_REQUEST),

    NOT_ENOUGH_MONEY("Not enough money to make transfer.", HttpStatus.BAD_REQUEST),;


    private final String message;

    private final HttpStatus httpStatus;


    ResponseDetails(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
