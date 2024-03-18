package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Transactions;
import com.devunited.examenfinalprog4.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public Transactions getTransactionById(int id) throws SQLException {
        return transactionRepository.getTransactionById(id);
    }

    @Override
    public Transactions createTransaction(Transactions transaction) throws SQLException {
        return transactionRepository.createTransaction(transaction);
    }

    @Override
    public Transactions updateTransaction(int id, Transactions transaction) throws SQLException {
        return transactionRepository.updateTransaction(id, transaction);
    }
}
