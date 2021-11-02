package com.chocolateboxproject.leadservice.dto;

import com.chocolateboxproject.leadservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {

    private Long id;

    private Industry industry;

    private int employeeCount;
    private String city;
    private String country;

    private List<ContactDTO> contactList;

    private List<OpportunityDTO> opportunityList;

    public AccountDTO(Long id, Industry industry, int employeeCount, String city, String country) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }


}
