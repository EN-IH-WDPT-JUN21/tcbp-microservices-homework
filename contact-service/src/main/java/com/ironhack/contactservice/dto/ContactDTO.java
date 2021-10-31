package com.ironhack.contactservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    @NotBlank(message = "Name cannot be empty or null.")
    private String contactName;

    // TODO: Add validators
    private String phoneNumber;
    private String email;

    @NotBlank(message = "Company name cannot be empty or null.")
    private String companyName;
}
