package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.Transactions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface TransactionRepository {
    List<Transactions> getAllTransactions() throws SQLException;
    Transactions getTransactionById(int id) throws SQLException;
    Transactions createTransaction(Transactions transaction) throws SQLException;
    Transactions updateTransaction(int id, Transactions transaction) throws SQLException;
    void updateAccountBalance(int id, BigDecimal amount) throws SQLException;
}
