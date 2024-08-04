package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.enums.AccountCurrency;
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
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private AccountCurrency accountCurrency;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false) @Builder.Default
    private Double balance = 0.0;

    @Column(nullable = false) @Builder.Default
    private Boolean isActive = true;

    @Column(nullable = false)
    private String otp;

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
                .accountNumber(this.accountNumber)
                .expiryDate(this.expiryMonth + "/" + this.expiryYear)
                .accountCurrency(this.accountCurrency)
                .cvv(this.cvv)
                .balance(this.balance)
                .isActive(this.isActive)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .otp(this.otp)
                .build();
    }
}
