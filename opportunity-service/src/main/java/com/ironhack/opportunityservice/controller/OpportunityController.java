package com.ironhack.opportunityservice.controller;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.interfaces.IOpportunityService;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {
    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    IOpportunityService opportunityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Opportunity> findAll() {
        return opportunityRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Opportunity findById(@PathVariable String id) {
        return opportunityRepository.findById(Long.valueOf(id)).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Opportunity create(@RequestBody @Valid OpportunityDTO opportunityDTO) {
        return opportunityService.store(opportunityDTO);
    }

    @PutMapping("/closed-lost/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Opportunity updateStatusClosedLost(@PathVariable String id) {
        return opportunityService.updateStatusClosedLost(id);
    }

    @PutMapping("/closed-won/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Opportunity updateStatusClosedWon(@PathVariable String id) {
        return opportunityService.updateStatusClosedWon(id);
    }
}
