package com.ironhack.opportunityservice.service;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.interfaces.IOpportunityService;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class OpportunityService implements IOpportunityService {
    @Autowired
    OpportunityRepository opportunityRepository;

    public Opportunity store(OpportunityDTO opportunityDTO) {
        Product product;
        switch(opportunityDTO.getProduct().toUpperCase()) {
            case "HYBRID":
                product = Product.HYBRID;
                break;
            case "FLATBED":
                product = Product.FLATBED;
                break;
            case "BOX":
                product = Product.BOX;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please use a valid product.");
        }

        Opportunity newOpportunity = new Opportunity(product, opportunityDTO.getQuantity(), opportunityDTO.getDecisionMaker(), Status.OPEN);
        return opportunityRepository.save(newOpportunity);
    }

    public Opportunity updateStatusClosedLost(String id) {
        Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(Long.valueOf(id));
        if(optionalOpportunity.isPresent()) {
            optionalOpportunity.get().setStatus(Status.CLOSED_LOST);
            return opportunityRepository.save(optionalOpportunity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Opportunity with this ID not found.");
        }
    }

    public Opportunity updateStatusClosedWon(String id) {
        Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(Long.valueOf(id));
        if(optionalOpportunity.isPresent()) {
            optionalOpportunity.get().setStatus(Status.CLOSED_WON);
            return opportunityRepository.save(optionalOpportunity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Opportunity with this ID not found.");
        }
    }
}
