package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("contact-service")
public interface ContactProxy {
    @GetMapping("/api/contact/{id}")
    Optional<ContactDTO> findById(@PathVariable String id);
}
