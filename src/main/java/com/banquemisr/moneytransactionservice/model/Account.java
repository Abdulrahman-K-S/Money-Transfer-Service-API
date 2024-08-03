package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.enums.AccountCurrency;
import com.banquemisr.moneytransactionservice.dto.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account { // add Expiry date (MM/YY) & cvv & otp
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountDescription; // Not used by applicatino

    @Column(nullable = false)
    private AccountType accountType; // Not used by application

    @Column(nullable = false)
    private AccountCurrency accountCurrency;

    @Column(nullable = false) @Builder.Default
    private Double balance = 0.0;

    @Column(nullable = false) @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    private User user;

    public AccountDTO ToDTO() {
        return AccountDTO.builder()
                .id(this.accountId)
                .accountName(this.accountName)
                .accountDescription(this.accountDescription)
                .accountNumber(this.accountNumber)
                .accountType(this.accountType)
                .accountCurrency(this.accountCurrency)
                .balance(this.balance)
                .isActive(this.isActive)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}

