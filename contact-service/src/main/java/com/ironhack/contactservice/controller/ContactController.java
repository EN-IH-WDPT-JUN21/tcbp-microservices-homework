package com.ironhack.contactservice.controller;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.interfaces.IContactService;
import com.ironhack.contactservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    IContactService contactService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contact findById(@PathVariable String id) {
        return contactRepository.findById(Long.valueOf(id)).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Contact create(@RequestBody @Valid ContactDTO contactDTO) {
        return contactService.store(contactDTO);
    }
}
