package com.aninfo.service;

import com.aninfo.TransactionType;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
    public Optional<Transaction> findById(Long cbu) {
        return transactionRepository.findById(cbu);
    }

    public Collection<Transaction> findByCbu(Long cbu) {
        return transactionRepository.findByCbu(cbu);
    }

    public Collection<Transaction> findByType(TransactionType type) {
        return transactionRepository.findByType(type);
    }
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
