package org.example.digital_banking.web;

import lombok.RequiredArgsConstructor;
import org.example.digital_banking.dtos.*;
import org.example.digital_banking.services.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public org.example.digital_banking.dto.DashboardStatsDTO getSummary() {
        return dashboardService.getDashboardStats();
    }

    @GetMapping("/account-stats")
    public List<AccountStatsDTO> getAccountStats() {
        return dashboardService.getAccountStats();
    }

    @GetMapping("/transaction-stats")
    public TransactionStatsDTO getTransactionStats(@RequestParam(defaultValue = "weekly") String timeFrame) {
        return dashboardService.getTransactionStats(timeFrame);
    }
}
