package com.chocolateboxproject.accountservice.service;

import com.chocolateboxproject.accountservice.dao.Account;
import com.chocolateboxproject.accountservice.dto.AccountDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Long id);

    boolean checkAccountExists(Long id);

    Account createNewAccount(AccountDTO accountDTO);

    Account updateAccount(Long id, AccountDTO accountDTO);

    Double getMeanEmployeeCount();

    Integer getMaxEmployeeCount();

    Integer getMinEmployeeCount();

    List<Integer> getEmployeeCountList();

    long countOpportunitiesByCountry(String country);

    long countOpportunitiesByCountryStatus(String country, String status);

    long countOpportunitiesByCity(String city);

    long countOpportunitiesByCityStatus(String city, String status);

    long countOpportunitiesByIndustry(String industry);

    long countOpportunitiesByIndustryStatus(String country, String status);
}
