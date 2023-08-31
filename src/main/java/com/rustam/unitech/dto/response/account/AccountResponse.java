package com.rustam.unitech.dto.response.account;

import com.rustam.unitech.entity.Account;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AccountResponse {
    private double amount;
    private String number;

    private boolean isActive;

    private LocalDateTime lastActivity;
    public static AccountResponse from(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.number = account.getNumber();
        accountResponse.amount = account.getAmount();
        accountResponse.lastActivity = account.getLastActivity();
        accountResponse.isActive = account.isActive();
        return accountResponse;
    }
}
