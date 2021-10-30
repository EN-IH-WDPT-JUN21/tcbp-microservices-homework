package com.ironhack.salesrepservice.dao;

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
@Table(name = "sales_rep")
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany
//            (
//                    mappedBy = "sales",
//                    cascade = CascadeType.MERGE,
//                    orphanRemoval = true
//            )
//    private List<LeadObject> leadList = new ArrayList<>();
//
//    @OneToMany
//            (
//                    mappedBy = "sales",
//                    cascade = CascadeType.MERGE,
//                    orphanRemoval = true
//            )
//
//    private List<Opportunity> opportunityList = new ArrayList<>();

    public SalesRep(String name) {
        this.name = name;
    }

//    public SalesRep(String name, List<LeadObject> leadList, List<Opportunity> opportunityList) {
//        this.name = name;
//        this.leadList = leadList;
//        this.opportunityList = opportunityList;
}

//    @Override
//    public String toString() {
//        return "SalesRep ID: " + this.getId() + ", SalesRep name: " + this.getName();
//    }
//}
//
