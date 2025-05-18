package org.example.digital_banking.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionStatsDTO {
    private List<String> dates;
    private List<Double> deposits;
    private List<Double> withdrawals;
    private List<Double> netChange;
}
