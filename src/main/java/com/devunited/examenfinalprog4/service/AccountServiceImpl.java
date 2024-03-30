package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountRepository.getAllAccounts();
    }

    @Override
    public Accounts getAccountById(int id) throws SQLException {
        return accountRepository.getAccountById(id);
    }

    @Override
    public Accounts createAccount(Accounts account) throws SQLException {
        return accountRepository.createAccount(account);
    }

    @Override
    public Accounts updateAccount(int id, Accounts account) throws SQLException {
        return accountRepository.updateAccount(id, account);
    }

    public boolean withdraw(int accountId, double amount) throws SQLException {
        Accounts account = accountRepository.getAccountById(accountId);
        if (account == null) {
            throw new SQLException("Account not found.");
        }
        double newBalance = account.getBalance() - amount;

        if (newBalance < 0 && (!account.isOverdraftEnabled() || newBalance < -account.getCreditLimit())) {
            return false;
        } else {
            account.setBalance(newBalance);
            accountRepository.updateAccount(accountId, account);
            return true;
        }
    }



}