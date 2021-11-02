package com.chocolateboxproject.leadservice.Proxy;

import com.chocolateboxproject.leadservice.dao.LeadObject;
import com.chocolateboxproject.leadservice.dto.ContactDTO;
import com.chocolateboxproject.leadservice.dto.LeadObjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("contact-service")
public interface ContactServiceProxy {

    @PostMapping("/api/contact")
    ContactDTO createNewContact(@RequestBody ContactDTO contactDTO);

}
