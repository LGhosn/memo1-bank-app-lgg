package com.aninfo.model;

import com.aninfo.TransactionType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long cbu;
    private Double amount;
    private TransactionType type;
    private LocalDateTime transactionInstant;

    public Transaction(Long cbu, Double amount, TransactionType type) {
        this.cbu = cbu;
        this.amount = amount;
        this.type = type;
        this.transactionInstant = LocalDateTime.now();
    }

    public Transaction() {

    }

    public Long getId() {
        return transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getCbu() {
        return cbu;
    }

    public TransactionType getTransactionType() {
        return type;
    }

    public LocalDateTime getTransactionInstant() {
        return transactionInstant;
    }
}
