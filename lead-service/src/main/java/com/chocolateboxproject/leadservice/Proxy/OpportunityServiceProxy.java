package com.chocolateboxproject.leadservice.Proxy;

import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.ContactDTO;
import com.chocolateboxproject.leadservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("opportunity-service")
public interface OpportunityServiceProxy {

    @PostMapping("/api/opportunity")
    OpportunityDTO createNewOpportunity(@RequestBody OpportunityDTO opportunityDTO);
}
