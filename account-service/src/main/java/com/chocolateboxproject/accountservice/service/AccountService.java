package com.chocolateboxproject.accountservice.service;

import com.chocolateboxproject.accountservice.dao.Account;
import com.chocolateboxproject.accountservice.dto.AccountDTO;
import com.chocolateboxproject.accountservice.repositories.AccountRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account Found with ID: " + id);
        return account.get();
    }

    @Override
    public boolean checkAccountExists(Long id){
        Optional<Account> account = accountRepository.findById(id);
        return account.isPresent();
    }

    @Override
    public Account createNewAccount(AccountDTO accountDTO){
        Account account = new Account(
                accountDTO.getIndustry(),
                accountDTO.getEmployeeCount(),
                accountDTO.getCity(),
                accountDTO.getCountry()
        );
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, AccountDTO accountDTO) {
        Account account = findById(id);
        account.setIndustry(accountDTO.getIndustry());
        account.setEmployeeCount(accountDTO.getEmployeeCount());
        account.setCity(accountDTO.getCity());
        account.setCountry(accountDTO.getCountry());
        return accountRepository.save(account);
    }

}
