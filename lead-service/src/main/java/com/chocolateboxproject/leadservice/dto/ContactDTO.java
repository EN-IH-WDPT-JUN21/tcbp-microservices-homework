package com.chocolateboxproject.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private Long id;
    private String contactName;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Long account;

    public ContactDTO(String contactName, String phoneNumber, String email, String companyName, Long account) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.account = account;
    }
}
