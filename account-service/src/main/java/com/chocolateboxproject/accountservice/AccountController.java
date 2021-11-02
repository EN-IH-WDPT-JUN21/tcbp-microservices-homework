package com.chocolateboxproject.accountservice;

import com.chocolateboxproject.accountservice.dao.Account;
import com.chocolateboxproject.accountservice.dto.AccountDTO;
import com.chocolateboxproject.accountservice.service.IAccountService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable Long id){
        return accountService.findById(id);
    }

    @GetMapping("/{id}/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkAccountExists(@PathVariable Long id){
        return accountService.checkAccountExists(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createNewAccount(accountDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account updateAccount(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid AccountDTO accountDTO){
        return accountService.updateAccount(id, accountDTO);
    }

}
