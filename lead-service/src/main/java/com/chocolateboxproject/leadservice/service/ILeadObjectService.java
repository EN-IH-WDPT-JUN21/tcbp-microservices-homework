package com.chocolateboxproject.leadservice.service;

import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.AccountDTO;
import com.chocolateboxproject.leadservice.dto.ContactDTO;
import com.chocolateboxproject.leadservice.dto.ConvertLeadDTO;
import com.chocolateboxproject.leadservice.dto.LeadObjectDTO;

import java.util.List;

public interface ILeadObjectService {
    LeadObject findById(Long id);

    List<LeadObject> findAll();

    long countLeadObjectsBySalesRep(String name);

    void deleteLead(long id);

    LeadObject createNewLeadObject(LeadObjectDTO leadObjectDTO);

    AccountDTO convertLead(long id, ConvertLeadDTO convertLeadDTO);
}
