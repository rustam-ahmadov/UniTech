package com.rustam.unitech.service;

import com.rustam.unitech.dto.request.account.AccountCreationRequest;
import com.rustam.unitech.dto.request.account.AccountRequest;
import com.rustam.unitech.dto.request.account.TransferRequest;
import com.rustam.unitech.dto.response.account.AccountResponse;
import com.rustam.unitech.entity.Account;
import com.rustam.unitech.entity.User;
import com.rustam.unitech.enums.ResponseDetails;
import com.rustam.unitech.exception.UniTechException;
import com.rustam.unitech.repository.AccountRepository;
import com.rustam.unitech.utils.CardNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final UserDetailsService userDetailsService;

    public AccountResponse find(final AccountRequest request) {
        final int pin = request.getPin();
        Account account = accountRepository.
                findByPin(pin)
                .orElseThrow(() -> new UniTechException(ResponseDetails.ACCOUNT_NOT_FOUND));
        return AccountResponse.from(account);
    }

    public AccountResponse create(final AccountCreationRequest request) {
        Optional<Account> optionalAccount = accountRepository.
                findByPin(request.getPin());
        if (optionalAccount.isPresent())
            throw new UniTechException(ResponseDetails.ACCOUNT_ALREADY_EXIST);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = new Account();
        account.setAmount(request.getAmount());
        account.setNumber(CardNumberGenerator.generate());
        account.setPin(request.getPin());
        account.setActive(true);
        account.setLastActivity(LocalDateTime.now());
        account.setUser(user);
        accountRepository.save(account);

        return AccountResponse.from(account);
    }

    public List<AccountResponse> getAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAccounts().stream()
                .map(AccountResponse::from)
                .filter(AccountResponse::isActive)
                .collect(Collectors.toList());
    }

    public ResponseDetails transfer(final TransferRequest request) {

        Account accountFrom = accountRepository.
                findByPin(request.getFromPin())
                .orElseThrow(() -> new UniTechException(ResponseDetails.ACCOUNT_NOT_FOUND));

        Account accountTo = accountRepository.
                findByPin(request.getToPin())
                .orElseThrow(() -> new UniTechException(ResponseDetails.TRANSFER_TO_NON_EXISTING_ACCOUNT));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User accountOwner = accountFrom.getUser();

        if (!(user.getId().equals(accountOwner.getId())))
            throw new UniTechException(ResponseDetails.ACCOUNT_NOT_FOUND);

        if (!accountFrom.isActive())
            throw new UniTechException(ResponseDetails.TRANSFER_FROM_INACTIVE_ACCOUNT);
        if (!accountTo.isActive())
            throw new UniTechException(ResponseDetails.TRANSFER_TO_INACTIVE_ACCOUNT);
        if (accountFrom == accountTo)
            throw new UniTechException(ResponseDetails.TRANSFER_TO_SAME_ACCOUNT);

        if (accountFrom.getAmount() < request.getAmount()) {
            throw new UniTechException(ResponseDetails.NOT_ENOUGH_MONEY);
        }

        double transferAmount = request.getAmount();
        double senderAmount = accountFrom.getAmount();
        double receiverAmount = accountTo.getAmount();

        double senderLeftAmount = senderAmount - transferAmount;
        accountFrom.setAmount(senderLeftAmount);

        double receiverLeftAmount = receiverAmount + transferAmount;
        accountTo.setAmount(receiverLeftAmount);

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);

        return ResponseDetails.TRANSACTION_COMPLETED;
    }
}
