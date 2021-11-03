package com.chocolateboxproject.leadservice.service;

import com.chocolateboxproject.leadservice.Proxy.AccountServiceProxy;
import com.chocolateboxproject.leadservice.Proxy.ContactServiceProxy;
import com.chocolateboxproject.leadservice.Proxy.OpportunityServiceProxy;
import com.chocolateboxproject.leadservice.Proxy.SalesRepServiceProxy;
import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.*;
import com.chocolateboxproject.leadservice.repositories.LeadObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeadObjectService implements ILeadObjectService {

    @Autowired
    private LeadObjectRepository leadObjectRepository;
    @Autowired
    private SalesRepServiceProxy salesRepServiceProxy;
    @Autowired
    private ContactServiceProxy contactServiceProxy;
    @Autowired
    private OpportunityServiceProxy opportunityServiceProxy;
    @Autowired
    private AccountServiceProxy accountServiceProxy;

    @Override
    public LeadObject findById(Long id){
        Optional<LeadObject> leadObject = leadObjectRepository.findById(id);
        if(leadObject.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Lead found with ID: " + id);
        return leadObject.get();
    }


    public List<LeadObject> findAll(){
        return leadObjectRepository.findAll();
    }

    @Override
    public long countLeadObjectsBySalesRep(String name){
        return leadObjectRepository.countLeadObjectsBySalesRep(name);
    }

    @Override
    public void deleteLead(long id){
        leadObjectRepository.delete(findById(id));
    }

    @Override
    public LeadObject createNewLeadObject(LeadObjectDTO leadObjectDTO) {
        LeadObject leadObject = new LeadObject();
        if(leadObjectDTO.getContactName().trim().equals("")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Contact Name Present");
        leadObject.setContactName(leadObjectDTO.getContactName());
        if(Objects.equals(leadObjectDTO.getPhoneNumber().trim(), "")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Phone Number Present");
        leadObject.setPhoneNumber(leadObjectDTO.getPhoneNumber());
        if(Objects.equals(leadObjectDTO.getEmail().trim(), "")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Email Present");
        leadObject.setEmail(leadObjectDTO.getEmail());
        if(Objects.equals(leadObjectDTO.getCompanyName().trim(), "")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Company Name Present");
        leadObject.setCompanyName(leadObjectDTO.getCompanyName());
        leadObject.setSalesId(salesRepServiceProxy.findById(leadObjectDTO.getSalesRep()).getId()); // This will validate if sales rep exists
        return leadObjectRepository.save(leadObject);
    }

    @Override
    public AccountDTO convertLead(long id, ConvertLeadDTO convertLeadDTO) {
        //Wrapper method for converting Lead and setting up the Account object
        AccountDTO accountDTO = new AccountDTO();
        if (accountServiceProxy.checkAccountExists(convertLeadDTO.getAccountId())) {
            accountDTO = accountServiceProxy.findById(convertLeadDTO.getAccountId());
        } else {
            accountDTO = new AccountDTO(
                    convertLeadDTO.getAccountId(),
                    convertLeadDTO.getIndustry(),
                    convertLeadDTO.getEmployeeCount(),
                    convertLeadDTO.getCity(),
                    convertLeadDTO.getCountry());
            accountDTO = accountServiceProxy.createAccount(accountDTO);
        }

        LeadObject leadObject = findById(id);
        ContactDTO contactDTO = new ContactDTO(
                leadObject.getContactName(),
                leadObject.getPhoneNumber(),
                leadObject.getEmail(),
                leadObject.getCompanyName(),
                accountDTO.getId()
        );
        contactDTO = contactServiceProxy.createNewContact(contactDTO);
        deleteLead(id);
        OpportunityDTO opportunityDTO = new OpportunityDTO(
                convertLeadDTO.getProduct(),
                convertLeadDTO.getQuantity(),
                contactDTO.getId(),
                accountDTO.getId(),
                leadObject.getSalesId()
        );
        opportunityDTO = opportunityServiceProxy.createNewOpportunity(opportunityDTO);
        return accountServiceProxy.findById(accountDTO.getId());
    }

}
