package com.ironhack.opportunityservice.interfaces;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOpportunityService {
    Opportunity store(OpportunityDTO opportunityDTO);
    Opportunity updateStatusClosedLost(String id);
    Opportunity updateStatusClosedWon(String id);

    Double getMeanOpportunityCountPerAccount();
    Integer getMaxOpportunityCountPerAccount();
    Integer getMinOpportunityCountPerAccount();
    List<Integer> getListOpportunityCountPerAccount();
    Double getMeanProductQuantity();
    Integer getMaxProductQuantity();
    Integer getMinProductQuantity();
    List<Integer> getProductQuantityList();

    long countOpportunitiesBySalesRep(String name);
    long countOpportunitiesBySalesRepAndStatus(String name, String status);
    long countOpportunitiesByProduct(String product);
    long countOpportunitiesByProductStatus(String product, String status);

}
