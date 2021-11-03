package com.ironhack.contactservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_generator")
    @SequenceGenerator(name="contact_generator", sequenceName = "contact_seq")
    private Long id;

    private String contactName;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Long account;

    public Contact(String contactName, String phoneNumber, String email, String companyName, Long account) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.account = account;
    }
}
