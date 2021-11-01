package com.ironhack.salesrepservice.service;


import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SalesRepService {

    @Autowired
    private SalesRepRepository salesRepRepository;

    public SalesRep newSalesRep(SalesRepDTO salesRepDTO) {

        SalesRep salesRep = new SalesRep(salesRepDTO.getName());
        return salesRepRepository.save(salesRep);
    }

    public SalesRep getSalesRepById(Long id) {
        Optional<SalesRep> optionalSalesRep = salesRepRepository.findById(id);
        if (optionalSalesRep.isPresent()) {
            return optionalSalesRep.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

    }

}
