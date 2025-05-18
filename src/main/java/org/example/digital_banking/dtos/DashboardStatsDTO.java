package org.example.digital_banking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardStatsDTO {
    private double totalBalance;
    private double balanceChange;
    private long activeAccounts;
    private long newAccounts;
    private long recentTransactions;
    private long pendingTransactions;
}
