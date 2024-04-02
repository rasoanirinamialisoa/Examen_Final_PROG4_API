package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Transactions;
import com.devunited.examenfinalprog4.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transactions> getAllTransactions() throws SQLException {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transactions getTransactionById(@PathVariable int id) throws SQLException {
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/transactions")
    public Transactions createTransaction(@RequestBody Transactions transaction) throws SQLException {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/transactions/{id}")
    public Transactions updateTransaction(@PathVariable int id, @RequestBody Transactions transaction) throws SQLException {
        return transactionService.updateTransaction(id, transaction);
    }
}
