# 💼 Digital Banking Management System - Spring Boot & Angular

![Java](https://img.shields.io/badge/Java-17-%23ED8B00)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-%236DB33F)
![Angular](https://img.shields.io/badge/Angular-15-%23DD0031)
![MySQL](https://img.shields.io/badge/MySQL-8.0-%234479A1)
![JWT](https://img.shields.io/badge/JWT-Auth-%23000000)

A **full-stack digital banking application** built using **Spring Boot (JEE)** for the backend and **Angular** for the frontend. This system allows management of customers, bank accounts, and financial operations with JWT-based authentication and clean REST APIs.

---

## 📖 Table of Contents

- [📸 Visual Documentation](#-visual-documentation)
- [🧪 Sample Data Initialization](#-sample-data-initialization)
- [🏛️ System Architecture](#-system-architecture)
- [🧰 Technologies Used](#-technologies-used)
- [📦 Database Design](#-database-design)
- [🔄 DTOs and Mapping](#-dtos-and-mapping)
- [💡 Service Layer Interface](#-service-layer-interface)
- [📬 API Testing Guide](#-api-testing-guide)
- [👨‍🏫 Credits](#-credits)

---

## 📸 Visual Documentation

### 🧱 Class Diagram

The core class diagram of the banking domain:

![Class Diagram](images/img.png)

---

## 🧪 Sample Data Initialization

Fake sample data is generated automatically at startup using a Spring `CommandLineRunner` bean:

```java
@Bean
CommandLineRunner initData() {
    return args -> {
        Customer c1 = customerRepository.save(new Customer("John Doe"));
        accountRepository.save(new CurrentAccount("ACC001", 1000, c1, 500));
    };
}
```

---

## 🏛️ System Architecture

This project uses the **Layered Architecture**:

- **Controller Layer** – Exposes RESTful APIs.
- **Service Layer** – Business logic and validation.
- **Repository Layer** – Data persistence with Spring Data JPA.
- **DTO Layer** – Ensures data exposure is clean and UI-friendly.
- **Mapper Layer** – Handles conversion between Entities and DTOs.

---

## 🧰 Technologies Used

- **Java 17**
- **Spring Boot 3.1**
- **Spring Data JPA**
- **Spring Security with JWT**
- **MySQL 8.0**
- **Angular 15**
- **Lombok**
- **Maven**

---

## 📦 Database Design

### 🧬 Inheritance Strategy

We use **Single Table Inheritance** for account types:

```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 4)
```

### 📋 Entity Snapshots

| Entity          | Screenshot                         | Description                                   |
|------------------|------------------------------------|-----------------------------------------------|
| **Bank Account** | ![BankAccount](/images/img2.png)   | Stores all accounts (with discriminator type) |
| **Customer**     | ![Customer](/images/img3.png)      | Contains client information                   |
| **Operation**    | ![Operation](/images/img1.png)     | Tracks credit/debit transactions              |

---

## 🔄 DTOs and Mapping

DTOs are used to transfer data from backend to frontend while hiding internal implementation details.

Example: `BankAccountDTO`

```java
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Long id;
    private double balance;
    private Date createdAt;
    private String status;
    private CustomerDTO customerDTO;
    private String type; // "SAV" or "CUR"
}
```

### 🛠 Mapper

A dedicated `BankAccountMapper` class handles the conversion between entities and DTOs:

```java
// BankAccount -> BankAccountDTO
// Operation -> AccountOperationDTO
```

📸 Example:

- DTO Example:  
  ![DTO Structure](/images/dto.png)
- Entity vs DTO:
  ![Entity](/images/backentitie.png) ![DTO](/images/backDto.png)

---

## 💡 Service Layer Interface

```java
public interface CustomerServiceinterface {

    // Customer management
    List<CustomerDTO> getAllClients();
    CustomerDTO getClientById(Long id);
    CustomerDTO createClient(CustomerDTO customerDTO);
    CustomerDTO updateClient(Long id, CustomerDTO customerDTO);
    boolean deleteClient(Long id);

    // Bank Account management
    List<BankAccountDTO> getAllAccounts();
    BankAccountDTO getAccount(Long id);
    BankAccountDTO createAccount(BankAccountRequestDTO accountDTO);
    BankAccountDTO updateAccount(Long id, BankAccountDTO bankAccountDTO);
    boolean deleteAccount(Long id);

    // Operations
    void debit(Long accountId, CreditDebitRequestDTO requestDTO);
    void credit(Long accountId, CreditDebitRequestDTO requestDTO);
    void transfer(TransferRequestDTO transferRequestDTO);
    List<AccountOperationDTO> getAccountOperations(Long accountId);
}
```

---

## 📬 API Testing Guide

### Without DTO (raw entity)
🔹 `GET http://localhost:8085/comptes`  
![Without DTO](/images/withoutdto.png)

### With DTO
🔹 `GET http://localhost:8085/comptes`  
![With DTO](/images/withdto.png)

### Other Endpoints

- 🔹 `GET http://localhost:8085/comptes`  
  Get all bank accounts  
  ![GET All](/images/img4.png)

- 🔹 `GET http://localhost:8085/comptes/1`  
  Get specific account by ID  
  ![GET One](/images/img5.png)

---

## 👨‍🏫 Credits

- 👨‍💻 **Developer**: Youssef Ouben Said – Student at ENSET Mohammedia
- 🎓 **Supervisor**: Mr. Mohammed Youssfi

This project was developed as part of a software engineering curriculum to explore full-stack enterprise development using Spring Boot and Angular.

---



**# Frontend
## This repository contains the frontend application for the Digital Banking Dashboard.

### GitHub Repository
You can find the full source code for the frontend on GitHub:
👉https://github.com/youssefoubens/DigitalBankingfrentend.git**

