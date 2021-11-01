package com.ironhack.validationservice.controller;


import com.ironhack.validationservice.dto.AccountDTO;
import com.ironhack.validationservice.dto.EmailDTO;
import com.ironhack.validationservice.dto.LeadDTO;
import com.ironhack.validationservice.dto.PhoneDTO;
import com.ironhack.validationservice.proxy.SalesRepServiceProxy;
import com.ironhack.validationservice.service.DataValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validation")
public class DataValidatorController {

    @Autowired
    private DataValidatorService dataValidatorService;


    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateEmail(@RequestBody EmailDTO emailDTO) {

        return dataValidatorService.validateEmail(emailDTO.getEmail());
    }

    @GetMapping("/phone")
    @ResponseStatus(HttpStatus.OK)
    public boolean validatePhone(@RequestBody PhoneDTO phoneDTO) {

        return dataValidatorService.validatePhoneNumber(phoneDTO.getPhoneNumber());
    }

    @GetMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateSalesRep(@PathVariable(name = "id") Long id) throws Exception {

        return dataValidatorService.salesRepExists(id);
    }

    @GetMapping("/lead/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateLead(@PathVariable(name = "id") Long id) throws Exception {

        return dataValidatorService.leadExists(id);
    }

    @GetMapping("/opportunity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateOpportunity(@PathVariable(name = "id") Long id) throws Exception {

        return dataValidatorService.opportunityExists(id);
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateAccount(@PathVariable(name = "id") Long id) throws Exception {

        return dataValidatorService.accountExists(id);
    }

    @GetMapping("/duplicate/lead")
    @ResponseStatus(HttpStatus.OK)
    public boolean leadDuplicate(@RequestBody LeadDTO leadDTO) {
        return dataValidatorService.isLeadDuplicate(leadDTO);
    }

    @GetMapping("/duplicate/account")
    @ResponseStatus(HttpStatus.OK)
    public boolean accountDuplicate(@RequestBody AccountDTO accountDTO) {
        return dataValidatorService.isAccountDuplicate(accountDTO);
    }










}
