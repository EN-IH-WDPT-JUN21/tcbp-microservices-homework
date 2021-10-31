package com.ironhack.salesrepservice.controller;


import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesrep")
public class SalesRepController {

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private SalesRepService salesRepService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRep> getAll() {
        return salesRepRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRep getById(@PathVariable(name = "id") Long id) {
        return salesRepService.getSalesRepById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRep createSalesRep(@RequestBody SalesRepDTO salesRepDTO) {
        
        return salesRepService.newSalesRep(salesRepDTO);

    }



}
