package com.rustam.unitech.dto.request.account;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreationRequest {

    @NotBlank
    private final int pin;

    @NotNull
    private final double amount;
}
