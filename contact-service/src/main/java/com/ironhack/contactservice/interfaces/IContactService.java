package com.ironhack.contactservice.interfaces;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;

public interface IContactService {
    Contact store(ContactDTO contactDTO);
}
