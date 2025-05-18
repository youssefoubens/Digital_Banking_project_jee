package org.example.digital_banking.repositories;

import org.example.digital_banking.entities.BankAccount;
import org.example.digital_banking.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
    @Query("SELECT SUM(b.balance) FROM BankAccount b")
    double sumAllBalances();

    @Query("SELECT TYPE(b), COUNT(b), SUM(b.balance) FROM BankAccount b GROUP BY TYPE(b)")
    List<Object[]> groupByTypeAndSumBalances();

    long countByStatus(AccountStatus status);


    @Query("SELECT COUNT(b) FROM BankAccount b WHERE b.createdAt >= :date")
    long countByCreatedAtAfter(@Param("date") Date date);
    // You can add custom queries here if needed
}
