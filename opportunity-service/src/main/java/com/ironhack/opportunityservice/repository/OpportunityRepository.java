package com.ironhack.opportunityservice.repository;

import com.ironhack.opportunityservice.dao.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Query(value="SELECT AVG(cast(opps as DOUBLE)) FROM (SELECT ACCOUNT, COUNT(*) AS opps FROM OPPORTUNITY GROUP BY ACCOUNT)", nativeQuery = true)
    Double getMeanOpportunityCountPerAccount();

    @Query(value="SELECT MAX(cast(opps as DOUBLE)) FROM (SELECT ACCOUNT, COUNT(*) AS opps FROM OPPORTUNITY GROUP BY ACCOUNT)", nativeQuery = true)
    Integer getMaxOpportunityCountPerAccount();

    @Query(value="SELECT MIN(cast(opps as DOUBLE)) FROM (SELECT ACCOUNT, COUNT(*) AS opps FROM OPPORTUNITY GROUP BY ACCOUNT)", nativeQuery = true)
    Integer getMinOpportunityCountPerAccount();

    @Query(value="SELECT opps FROM (SELECT ACCOUNT, COUNT(*) AS opps FROM OPPORTUNITY GROUP BY ACCOUNT)", nativeQuery = true)
    List<Integer> getListOpportunityCountPerAccount();

    @Query(value = "SELECT AVG(cast(quantity AS DOUBLE)) as quantityCount FROM OPPORTUNITY", nativeQuery = true)
    Double getMeanProductQuantity();

    @Query(value = "SELECT MAX(quantity) as quantityCount FROM OPPORTUNITY", nativeQuery = true)
    Integer getMaxProductQuantity();

    @Query(value = "SELECT MIN(quantity) as quantityCount FROM OPPORTUNITY", nativeQuery = true)
    Integer getMinProductQuantity();

    @Query(value = "SELECT quantity FROM OPPORTUNITY", nativeQuery = true)
    List<Integer> getProductQuantityList();

    //Report by SalesRep
    @Query(
            value = "SELECT COUNT(opportunity.id) FROM opportunity INNER JOIN sales_rep ON sales_rep.id = opportunity.sales_id WHERE sales_rep.name = :name",
            nativeQuery = true
    )
    long countOpportunitiesBySalesRep(@Param("name") String name);

    @Query(
            value = "SELECT COUNT(opportunity.id) " +
                    "FROM opportunity " +
                    "INNER JOIN sales_rep ON sales_rep.id = opportunity.sales_id " +
                    "WHERE sales_rep.name = :name AND opportunity.status = :status",
            nativeQuery = true
    )
    long countOpportunitiesBySalesRepAndStatus(@Param("name") String name, @Param("status") String status);

    //Report by Product
    @Query(
            value = "SELECT COUNT(opportunity.id) " +
                    "FROM opportunity " +
                    "WHERE opportunity.product = :product",
            nativeQuery = true
    )
    long countOpportunitiesByProduct(@Param("product") String product);

    @Query(
            value = "SELECT COUNT(opportunity.id) " +
                    "FROM opportunity " +
                    "WHERE opportunity.product = :product AND opportunity.status = :status",
            nativeQuery = true
    )
    long countOpportunitiesByProductStatus(@Param("product") String product, @Param("status") String status);
}
