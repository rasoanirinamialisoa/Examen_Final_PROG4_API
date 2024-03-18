package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Transactions;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {
    List<Transactions> getAllTransactions() throws SQLException;
    Transactions getTransactionById(int id) throws SQLException;
    Transactions createTransaction(Transactions transaction) throws SQLException;
    Transactions updateTransaction(int id, Transactions transaction) throws SQLException;
}
