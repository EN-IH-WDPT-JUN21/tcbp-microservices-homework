package com.chocolateboxproject.accountservice.controller;

import com.chocolateboxproject.accountservice.dao.Account;
import com.chocolateboxproject.accountservice.dto.AccountDTO;
import com.chocolateboxproject.accountservice.service.IAccountService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable Long id){
        return accountService.findById(id);
    }

    @GetMapping("/{id}/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkAccountExists(@PathVariable Long id){
        return accountService.checkAccountExists(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createNewAccount(accountDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account updateAccount(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid AccountDTO accountDTO){
        return accountService.updateAccount(id, accountDTO);
    }

    @GetMapping("/report/employee-count/mean")
    @ResponseStatus(HttpStatus.OK)
    public Double getMeanEmployeeCount() {
        return accountService.getMeanEmployeeCount();
    }

    @GetMapping("/report/employee-count/max")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMaxEmployeeCount() {
        return accountService.getMaxEmployeeCount();
    }

    @GetMapping("/report/employee-count/min")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMinEmployeeCount() {
        return accountService.getMinEmployeeCount();
    }

    // TODO: Median?

    @GetMapping("/report/opportunity/country/{country}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByCountry(@PathVariable("country") String country) {
        return accountService.countOpportunitiesByCountry(country);
    }

    @GetMapping("/report/opportunity/country-status/{country}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByCountryStatus(@PathVariable("country") String country, @PathVariable("status") String status) {
        return accountService.countOpportunitiesByCountryStatus(country, status);
    }

    @GetMapping("/report/opportunity/city/{city}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByCity(@PathVariable("city") String city) {
        return accountService.countOpportunitiesByCity(city);
    }

    @GetMapping("/report/opportunity/city-status/{city}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByCityStatus(@PathVariable("city") String city, @PathVariable("status") String status) {
        return accountService.countOpportunitiesByCityStatus(city, status);
    }

    @GetMapping("/report/opportunity/industry/{industry}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByIndustry(@PathVariable("industry") String industry) {
        return accountService.countOpportunitiesByIndustry(industry);
    }

    @GetMapping("/report/opportunity/industry-status/{industry}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public long countOpportunitiesByIndustryStatus(@PathVariable("industry") String industry, @PathVariable("status") String status) {
        return accountService.countOpportunitiesByIndustryStatus(industry, status);
    }
}
