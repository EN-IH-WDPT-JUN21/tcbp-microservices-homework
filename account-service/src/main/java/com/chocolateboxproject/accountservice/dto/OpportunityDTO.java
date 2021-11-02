package com.chocolateboxproject.accountservice.dto;

import com.chocolateboxproject.accountservice.enums.Product;
import com.chocolateboxproject.accountservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityDTO {

    private Long id;
    private Product product;
    private int quantity;
    private Long decisionMaker;
    private Status status = Status.OPEN;
    private Long Account;
    private Long salesRep;

    public OpportunityDTO(Product product, int quantity, Long decisionMaker, Long account, Long salesRep) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.Account = account;
        this.salesRep = salesRep;
    }
}
