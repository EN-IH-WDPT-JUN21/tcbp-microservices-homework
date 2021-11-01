package com.ironhack.validationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "localhost:8005/api/account")
public interface AccountServiceProxy {

    @GetMapping("/{id}")
    String getAccountById(@PathVariable(name = "id") Long id);

    @GetMapping("")
    String getAllAccounts();
}
