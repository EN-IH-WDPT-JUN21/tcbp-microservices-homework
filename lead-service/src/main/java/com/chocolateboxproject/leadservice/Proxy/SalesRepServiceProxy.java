package com.chocolateboxproject.leadservice.Proxy;

import com.chocolateboxproject.leadservice.dto.SalesRep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SalesRep-Service")
public interface SalesRepServiceProxy {

    @GetMapping("/api/salesrep/{id}")
    SalesRep findById(@PathVariable(name="id")long id);

}
