package com.ironhack.contactservice.service;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.interfaces.IContactService;
import com.ironhack.contactservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact store(ContactDTO contactDTO) {
        Contact newContact = new Contact(contactDTO.getContactName(), contactDTO.getPhoneNumber(), contactDTO.getEmail(), contactDTO.getCompanyName());
        return contactRepository.save(newContact);
    }
}
