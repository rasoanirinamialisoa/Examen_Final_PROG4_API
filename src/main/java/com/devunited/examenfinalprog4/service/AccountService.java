package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Accounts;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    List<Accounts> getAllAccounts() throws SQLException;
    Accounts getAccountById(int id) throws SQLException;
    Accounts createAccount(Accounts account) throws SQLException;
    Accounts updateAccount(int id, Accounts account) throws SQLException;
    boolean withdraw(int id, double amount, int accountTypeId) throws SQLException;
    void updateAccountBalance(int id, BigDecimal amount) throws SQLException;
}
