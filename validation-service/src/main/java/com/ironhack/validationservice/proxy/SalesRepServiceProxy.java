package com.ironhack.validationservice.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "salesrep-service", url = "localhost:8001/api/salesrep")
public interface SalesRepServiceProxy {

    @GetMapping("/{id}")
    String getSalesRepById(@PathVariable(name = "id") Long id);
}
