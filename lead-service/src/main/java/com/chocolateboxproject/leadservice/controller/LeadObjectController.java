package com.chocolateboxproject.leadservice.controller;

import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.AccountDTO;
import com.chocolateboxproject.leadservice.dto.ConvertLeadDTO;
import com.chocolateboxproject.leadservice.dto.LeadObjectDTO;
import com.chocolateboxproject.leadservice.service.ILeadObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lead")
public class LeadObjectController {

    @Autowired
    private ILeadObjectService leadObjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LeadObject> findAll(){
        return leadObjectService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadObject findById(@PathVariable(name="id") long id){
        return leadObjectService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable(name="id") long id){
        leadObjectService.deleteLead(id);
    }

    @PutMapping("/convert/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO convertLead(@PathVariable(name="id") long id,
                                           @RequestBody ConvertLeadDTO convertLeadDTO){
        return leadObjectService.convertLead(id, convertLeadDTO);
    }

    @GetMapping("/count-all")
    @ResponseStatus(HttpStatus.OK)
    public long countAll(){
        return leadObjectService.findAll().size();
    }

    @GetMapping("/count/{salesrep}")
    @ResponseStatus(HttpStatus.OK)
    public long getCountBySalesRepName(@PathVariable(name="salesrep") String name){
        return leadObjectService.countLeadObjectsBySalesRep(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LeadObject createNewLeadObject(@RequestBody @Valid LeadObjectDTO leadObjectDTO){
        return leadObjectService.createNewLeadObject(leadObjectDTO);
    }



}
