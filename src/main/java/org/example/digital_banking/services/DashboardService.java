package org.example.digital_banking.services;

import org.example.digital_banking.dtos.AccountStatsDTO;

import org.example.digital_banking.dtos.TransactionStatsDTO;

import java.util.List;

public interface DashboardService {
    org.example.digital_banking.dto.DashboardStatsDTO getDashboardStats();
    List<AccountStatsDTO> getAccountStats();
    TransactionStatsDTO getTransactionStats(String timeFrame);
}
