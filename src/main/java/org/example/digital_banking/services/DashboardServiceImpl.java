package org.example.digital_banking.services;

import lombok.RequiredArgsConstructor;
import org.example.digital_banking.dtos.*;
import org.example.digital_banking.enums.AccountStatus;
import org.example.digital_banking.repositories.*;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BankAccountRepo bankAccountRepo;
    private final OperationRepo operationRepo;


    @Override
    public org.example.digital_banking.dtos.DashboardStatsDTO getDashboardStats() {
        double totalBalance = bankAccountRepo.sumAllBalances();
        long activeAccounts = bankAccountRepo.countByStatus(AccountStatus.ACTIVE);
        long newAccounts = bankAccountRepo.countByCreatedAtAfter(new Date(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000));
        long recentTransactions =  countRecentTransactions(7); // last 7 days
        long pendingTransactions = operationRepo.countByDescriptionContainingIgnoreCase("pending");

        return org.example.digital_banking.dtos.DashboardStatsDTO.builder()
                .totalBalance(totalBalance)
                .balanceChange(0) // Placeholder, compute net change if needed
                .activeAccounts(activeAccounts)
                .newAccounts(newAccounts)
                .recentTransactions(recentTransactions)
                .pendingTransactions(pendingTransactions)
                .build();
    }
    public long countRecentTransactions(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -days);
        Date startDate = cal.getTime();

        return operationRepo.countRecent(startDate);
    }
    @Override
    public List<AccountStatsDTO> getAccountStats() {
        List<Object[]> results = bankAccountRepo.groupByTypeAndSumBalances();

        List<AccountStatsDTO> dtos = new ArrayList<>();
        for (Object[] row : results) {
            dtos.add(AccountStatsDTO.builder()
                    .type((String) row[0])
                    .count(((Number) row[1]).longValue())
                    .totalBalance(((Number) row[2]).doubleValue())
                    .build());
        }
        return dtos;
    }

    @Override
    public TransactionStatsDTO getTransactionStats(String timeFrame) {
        // Logic: Aggregate operation data based on timeFrame
        // You can use native queries or spring projections here
        List<String> dates = new ArrayList<>();
        List<Double> deposits = new ArrayList<>();
        List<Double> withdrawals = new ArrayList<>();
        List<Double> netChange = new ArrayList<>();

        // For demo purposes, add mock data
        for (int i = 6; i >= 0; i--) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis() - i * 86400000L));
            dates.add(date);
            deposits.add(Math.random() * 1000);
            withdrawals.add(Math.random() * 800);
            netChange.add(deposits.get(deposits.size() - 1) - withdrawals.get(withdrawals.size() - 1));
        }

        return TransactionStatsDTO.builder()
                .dates(dates)
                .deposits(deposits)
                .withdrawals(withdrawals)
                .netChange(netChange)
                .build();
    }
}
