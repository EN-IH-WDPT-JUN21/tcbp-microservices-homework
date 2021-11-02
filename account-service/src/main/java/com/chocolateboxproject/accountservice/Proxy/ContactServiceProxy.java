package com.chocolateboxproject.accountservice.Proxy;


import com.chocolateboxproject.accountservice.dto.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("contact-service")
public interface ContactServiceProxy {

    @PostMapping("/api/contact")
    ContactDTO createNewContact(@RequestBody ContactDTO contactDTO);

}
