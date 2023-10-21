package com.aninfo.repository;

import com.aninfo.TransactionType;
import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByCbu(Long cbu);

//    Optional<Transaction> findById(Long transactionId);

    List<Transaction> findByType(TransactionType transactionType);

    @Override
    List<Transaction> findAll();
}
