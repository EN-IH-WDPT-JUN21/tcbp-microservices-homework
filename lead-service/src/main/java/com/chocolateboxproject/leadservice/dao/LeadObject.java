package com.chocolateboxproject.leadservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lead_object")
public class LeadObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_generator")
    @SequenceGenerator(name="lead_generator", sequenceName = "lead_seq")
    protected Long id;

    private String contactName;
    private String phoneNumber;
    private String email;
    private String companyName;

    private long sales;

    public LeadObject(String contactName, String phoneNumber, String email, String companyName, long sales) {
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setSales(sales);
    }

    // Checks that leads are not duplicated on each attribute except ID. As the ID is auto-incremented, it is assumed
    // that they will not be repeated.
    @Override
    public boolean equals(Object l) {
        if (this == l) return true;
        if (l == null || getClass() != l.getClass()) return false;
        LeadObject that = (LeadObject) l;
        return this.getCompanyName() == that.getCompanyName() &&
                this.getEmail() == that.getEmail() && this.getContactName() == that.getContactName() &&
                this.getPhoneNumber() == that.getPhoneNumber();
    }

    @Override
    public String toString() {
        return "Lead: " + this.getId() + ", Contact: " + this.getContactName() + ", Phone Number: " +
                this.getPhoneNumber() + ", Email: " + this.getEmail() + ", Company Name: " + this.getCompanyName();
    }
}
