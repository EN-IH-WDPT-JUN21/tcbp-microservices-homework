package com.chocolateboxproject.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadObjectDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty or null.")
    private String contactName;

    @Pattern(regexp = "^\\+?\\d{6,15}")
    private String phoneNumber;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "Company name cannot be empty or null.")
    private String companyName;

    private Long salesRep;

    public LeadObjectDTO(String contactName, String phoneNumber, String email, String companyName, Long salesRep) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;
    }
}
