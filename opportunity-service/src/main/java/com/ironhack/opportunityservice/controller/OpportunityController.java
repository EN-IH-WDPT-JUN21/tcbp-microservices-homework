package com.ironhack.opportunityservice.controller;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.interfaces.IOpportunityService;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/report/mean")
    @ResponseStatus(HttpStatus.OK)
    public Double getMeanOpportunityCountPerAccount() {
        return opportunityService.getMeanOpportunityCountPerAccount();
    }

    @GetMapping("/report/max")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMaxOpportunityCountPerAccount() {
        return opportunityService.getMaxOpportunityCountPerAccount();
    }

    @GetMapping("/report/min")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMinOpportunityCountPerAccount() {
        return opportunityService.getMinOpportunityCountPerAccount();
    }

    // TODO: Median?

    @GetMapping("/report/product-quantity/mean")
    @ResponseStatus(HttpStatus.OK)
    public Double getMeanProductQuantity() {
        return opportunityService.getMeanProductQuantity();
    }

    @GetMapping("/report/product-quantity/max")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMaxProductQuantity() {
        return opportunityService.getMaxProductQuantity();
    }

    @GetMapping("/report/product-quantity/min")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMinProductQuantity() {
        return opportunityService.getMinProductQuantity();
    }

    // TODO: Median for product quantity?

    @GetMapping("/report/salesrep/{salesRepName}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesBySalesRep(@PathVariable("salesRepName") String name) {
        return opportunityService.countOpportunitiesBySalesRep(name);
    }

    @GetMapping("/report/salesrep-status/{salesRepName}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesBySalesRepAndStatus(@PathVariable("salesRepName") String name, @PathVariable("status") String status) {
        return opportunityService.countOpportunitiesBySalesRepAndStatus(name, status);
    }

    @GetMapping("/report/product/{product}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByProduct(@PathVariable("product") String product) {
        return opportunityService.countOpportunitiesByProduct(product);
    }

    @GetMapping("/report/product-status/{product}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByProductStatus(@PathVariable("product") String product, @PathVariable("status") String status) {
        return opportunityService.countOpportunitiesByProductStatus(product, status);
    }
}
