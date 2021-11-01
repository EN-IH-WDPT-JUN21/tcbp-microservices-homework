package com.ironhack.validationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountDTO {

    private String industry;
    private int employeeCount;
    private String city;
    private String country;

}
