package com.rustam.unitech.dto.request.account;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreationRequest {

    //it s better to write it string and write some regExp
    @NotNull
    private final Integer pin;

    @NotNull
    private final Double amount;
}
