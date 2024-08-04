package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private Accounts fromAccount;

    @ManyToOne
    private Accounts toAccount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public TransactionDTO toDTO() {
        return TransactionDTO
                .builder()
                .fromAccountNumber(this.fromAccount.getAccountNumber())
                .toAccountNumber(this.toAccount.getAccountNumber())
                .amount(this.amount)
                .build();
    }
}