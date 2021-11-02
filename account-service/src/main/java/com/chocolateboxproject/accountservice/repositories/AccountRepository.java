package com.chocolateboxproject.accountservice.repositories;

import com.chocolateboxproject.accountservice.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
