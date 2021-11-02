package com.chocolateboxproject.accountservice.Proxy;

import com.chocolateboxproject.accountservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("opportunity-proxy")
public interface OpportunityServiceProxy {

    @PostMapping("/api/opportunity")
    OpportunityDTO createNewOpportunity(@RequestBody OpportunityDTO opportunityDTO);
}
