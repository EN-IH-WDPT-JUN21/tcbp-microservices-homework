package com.ironhack.validationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LeadDTO {

    private String contactName;
    private String phoneNumber;
    private String email;
    private String companyName;
}
