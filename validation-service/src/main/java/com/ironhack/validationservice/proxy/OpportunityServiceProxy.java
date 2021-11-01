package com.ironhack.validationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "opportunity-service", url = "localhost:8004/api/opportunity")
public interface OpportunityServiceProxy {

    @GetMapping("/{id}")
    String getOpportunityById(@PathVariable(name = "id") Long id);
}
