package com.rustam.unitech.service;

import com.rustam.unitech.dto.request.account.AccountCreationRequest;
import com.rustam.unitech.dto.request.account.TransferRequest;
import com.rustam.unitech.dto.response.account.AccountResponse;

import java.util.List;

public interface AccountServiceInterface {

     AccountResponse create(final AccountCreationRequest request);

     List<AccountResponse> getAll() ;

     String transfer(final TransferRequest request);
}
