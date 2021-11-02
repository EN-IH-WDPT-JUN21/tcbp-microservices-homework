package com.chocolateboxproject.accountservice.service;

import com.chocolateboxproject.accountservice.dao.Account;
import com.chocolateboxproject.accountservice.dto.AccountDTO;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Long id);

    boolean checkAccountExists(Long id);

    Account createNewAccount(AccountDTO accountDTO);

    Account updateAccount(Long id, AccountDTO accountDTO);
}
