package org.example.digital_banking.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountStatsDTO {
    private String type;
    private long count;
    private double totalBalance;
}
