package com.ironhack.validationservice.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "lead-service", url = "localhost:8002/api/lead")
public interface LeadServiceProxy {

    @GetMapping("/{id}")
    String getLeadById(@PathVariable(name = "id") Long id);

    @GetMapping("")
    String getLeads();



}
