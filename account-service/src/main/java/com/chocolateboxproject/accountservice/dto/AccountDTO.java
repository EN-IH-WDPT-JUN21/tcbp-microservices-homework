package com.chocolateboxproject.accountservice.dto;


import com.chocolateboxproject.accountservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {

    private Long id;

    @NotNull
    private Industry industry;

    @Min(value = 1, message = "Employee Count Must 1 Or More")
    private int employeeCount;
    @NotNull
    private String city;
    @NotNull
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
