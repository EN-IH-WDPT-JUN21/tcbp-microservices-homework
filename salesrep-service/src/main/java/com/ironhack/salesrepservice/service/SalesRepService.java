package com.ironhack.salesrepservice.service;


import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRepService {

    @Autowired
    private SalesRepRepository salesRepRepository;

    public SalesRep newSalesRep(SalesRepDTO salesRepDTO) {

        SalesRep salesRep = new SalesRep(salesRepDTO.getName());
        return salesRepRepository.save(salesRep);
    }

}
