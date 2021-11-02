package com.chocolateboxproject.leadservice.Proxy;

import com.chocolateboxproject.leadservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface AccountServiceProxy {

    @GetMapping("/api/account/{id}")
    AccountDTO findById(@PathVariable long id);

    @PostMapping("/api/account")
    AccountDTO createAccount(@RequestBody AccountDTO accountDTO);
}
