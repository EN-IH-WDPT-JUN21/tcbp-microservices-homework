package com.ironhack.opportunityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {

    @NotBlank(message = "Product cannot be empty or null.")
    private String product;

    @NotNull
    private int quantity;

    @NotNull
    private Long decisionMaker;

    private String status;
}
