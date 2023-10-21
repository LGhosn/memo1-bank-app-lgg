package com.aninfo.service;

import com.aninfo.TransactionType;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    private static final double EXTRA_PERCENT = 0.10;
    private static final int MIN_SUM_FOR_PROMO = 2000;

    private static final int LIMIT_EXTRA = 500;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account.getCbu(), sum, TransactionType.WITHDRAW);
        transactionRepository.save(transaction);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        if ( sum >= MIN_SUM_FOR_PROMO) {
            var extra_sum = (sum * EXTRA_PERCENT);
            var extra =  extra_sum > LIMIT_EXTRA ? LIMIT_EXTRA : extra_sum;
            sum += extra;
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account.getCbu(), sum, TransactionType.DEPOSIT);
        transactionRepository.save(transaction);

        return account;
    }

}
