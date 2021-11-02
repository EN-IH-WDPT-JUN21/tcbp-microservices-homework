package com.chocolateboxproject.leadservice.dto;

import com.chocolateboxproject.leadservice.enums.Industry;
import com.chocolateboxproject.leadservice.enums.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertLeadDTO {

    private Long leadId;
    private Product product;
    private Integer quantity;
    private Long accountId;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;


}
