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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opportunity_generator")
    @SequenceGenerator(name="opportunity_generator", sequenceName = "opportunity_seq")
    private Long id;

    private Product product;
    private int quantity;

    private Long decisionMaker;
    private Status status;

    public Opportunity(Product product, int quantity, Long decisionMaker, Status status) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
    }
}
