package com.banquemisr.moneytransactionservice.model;

import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTransactions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Transaction transaction;

    private String status = "pending"; // Create an enum for status

    public UserTransactionDTO toDTO() {
        return UserTransactionDTO
                .builder()
                .userId(user.getCustomerId())
                .transactionId(transaction.getTransactionId())
                .status(this.status)
                .build();
    }
}
