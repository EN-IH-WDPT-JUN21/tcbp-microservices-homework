package com.ironhack.opportunityservice.service;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.ContactDTO;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.interfaces.IOpportunityService;
import com.ironhack.opportunityservice.proxy.ContactProxy;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService implements IOpportunityService {
    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    ContactProxy contactProxy;

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

        Optional<ContactDTO> contactDTO;
        try {
            contactDTO = contactProxy.findById(opportunityDTO.getDecisionMaker().toString());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact does not exist.");
        }

        if(contactDTO.isPresent()) {
            Opportunity newOpportunity = new Opportunity(
                    product,
                    opportunityDTO.getQuantity(),
                    opportunityDTO.getDecisionMaker(),
                    Status.OPEN,
                    opportunityDTO.getAccount(),
                    opportunityDTO.getSalesId());
            return opportunityRepository.save(newOpportunity);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact does not exist.");
        }
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

    @Override
    public Double getMeanOpportunityCountPerAccount() {
        return opportunityRepository.getMeanOpportunityCountPerAccount();
    }

    @Override
    public Integer getMaxOpportunityCountPerAccount() {
        return opportunityRepository.getMaxOpportunityCountPerAccount();
    }

    @Override
    public Integer getMinOpportunityCountPerAccount() {
        return opportunityRepository.getMinOpportunityCountPerAccount();
    }

    @Override
    public List<Integer> getListOpportunityCountPerAccount() {
        return opportunityRepository.getListOpportunityCountPerAccount();
    }

    @Override
    public Double getMeanProductQuantity() {
        return opportunityRepository.getMeanProductQuantity();
    }

    @Override
    public Integer getMaxProductQuantity() {
        return opportunityRepository.getMaxProductQuantity();
    }

    @Override
    public Integer getMinProductQuantity() {
        return opportunityRepository.getMinProductQuantity();
    }

    @Override
    public List<Integer> getProductQuantityList() {
        return opportunityRepository.getProductQuantityList();
    }

    @Override
    public long countOpportunitiesBySalesRep(String name) {
        return opportunityRepository.countOpportunitiesBySalesRep(name);
    }

    @Override
    public long countOpportunitiesBySalesRepAndStatus(String name, String status) {
        return opportunityRepository.countOpportunitiesBySalesRepAndStatus(name, status);
    }

    @Override
    public long countOpportunitiesByProduct(String product) {
        return opportunityRepository.countOpportunitiesByProduct(product);
    }

    @Override
    public long countOpportunitiesByProductStatus(String product, String status) {
        return opportunityRepository.countOpportunitiesByProductStatus(product, status);
    }


}
