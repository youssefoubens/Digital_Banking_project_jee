package org.example.digital_banking;

import org.example.digital_banking.entities.*;
import org.example.digital_banking.enums.AccountStatus;
import org.example.digital_banking.enums.Operation_type;
import org.example.digital_banking.repositories.BankAccountRepo;
import org.example.digital_banking.repositories.CustomerRepo;
import org.example.digital_banking.repositories.OperationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo,
                                               BankAccountRepo bankAccountRepo,
                                               OperationRepo operationRepo) {
        return args -> {
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                // Create customer
                Customer customer = Customer.builder()
                        .phone("0654823654" + "000" + i)
                        .email(i + "abc@gmail.com")
                        .city("Mohammedia" + i)
                        .name("youssef" + i)
                        .build();
                customerRepo.save(customer);

                BankAccount account;
                if (i % 2 == 0) {
                    // Create saving account
                    account = SavingAccount.builder()
                            .currency("MAD")
                            .status(AccountStatus.ACTIVE)
                            .customer(customer)
                            .interestRate(2.5 * i)
                            .balance(Math.random() * 10000)
                            .build();
                } else {
                    // Create current account
                    account = CurrentAccount.builder()
                            .currency("MAD")
                            .status(AccountStatus.ACTIVE)
                            .customer(customer)
                            .overdraft(5000.0 * i)
                            .balance(Math.random() * 10000)
                            .build();
                }
                bankAccountRepo.save(account);

                // Add operations to the account
                for (int j = 0; j < 5; j++) { // Add 5 operations per account
                    Operation operation = Operation.builder()
                            .operationType(j % 2 == 0 ? Operation_type.DEBIT : Operation_type.CREDIT)
                            .amount(random.nextDouble() * 1000)
                            .operationDate(new Date())
                            .description(j % 2 == 0 ? "Withdrawal" : "Deposit")
                            .bankAccount(account)
                            .build();
                    operationRepo.save(operation);

                    // Update account balance based on operation
                    if (operation.getOperationType() == Operation_type.CREDIT) {
                        account.setBalance(account.getBalance() + operation.getAmount());
                    } else {
                        account.setBalance(account.getBalance() - operation.getAmount());
                    }
                    bankAccountRepo.save(account);
                }
            }
        };
    }
}