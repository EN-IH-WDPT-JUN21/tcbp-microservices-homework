package com.ironhack.opportunityservice.interfaces;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;

public interface IOpportunityService {
    Opportunity store(OpportunityDTO opportunityDTO);
    Opportunity updateStatusClosedLost(String id);
    Opportunity updateStatusClosedWon(String id);

}
