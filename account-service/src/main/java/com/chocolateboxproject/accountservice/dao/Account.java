package com.chocolateboxproject.accountservice.dao;

import com.chocolateboxproject.accountservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    @Column(name="employee_count")
    private Integer employeeCount;

    private String city;
    private String country;

    public Account(Industry industry, Integer employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }
}
