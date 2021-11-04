package com.ironhack.opportunityservice.dao;

import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;

    private Long decisionMaker;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long account;
    private Long salesId;

    public Opportunity(Product product, int quantity, Long decisionMaker, Status status, Long account, Long salesId) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.account = account;
        this.salesId = salesId;
    }
}
