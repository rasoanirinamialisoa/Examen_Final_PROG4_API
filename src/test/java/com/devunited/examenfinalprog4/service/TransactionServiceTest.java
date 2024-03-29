package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Transactions;
import com.devunited.examenfinalprog4.repository.LoanRepository;
import com.devunited.examenfinalprog4.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void testGetAllTransactions() throws Exception{
        List<Transactions> transactionsList = new ArrayList<>();
        transactionsList.add(new Transactions(1, "Deposit", LocalDate.of(2023,
                1, 1), 100.0, 1, 4));
        transactionsList.add(new Transactions(2, "Withdraw", LocalDate.of(2023,
                2, 15), 50.0, 2, 1));

        when(transactionRepository.getAllTransactions()).thenReturn(transactionsList);

        List<Transactions> retrievedTransactions = transactionService.getAllTransactions();

        assertEquals(transactionsList.size(), retrievedTransactions.size());
        for (int i = 0; i < transactionsList.size(); i++) {
            assertEquals(transactionsList.get(i), retrievedTransactions.get(i));
        }
    }
    @Test
    public void testCreateTransaction() throws SQLException {
        Transactions transactionToAdd = new Transactions(11, "Deposit", LocalDate.now(),
                10.0, 9, 2);
        when(transactionRepository.createTransaction(any(Transactions.class))).thenReturn(transactionToAdd);
        Transactions addedTransaction = transactionService.createTransaction(transactionToAdd);
        assertEquals(transactionToAdd, addedTransaction);
    }

    @Test
    public void testGetTransactionById() throws SQLException {
        int transactionId = 1;
        Transactions existingTransaction = new Transactions(1, "Deposit", LocalDate.of(2023,
                1, 1), 200.0, 1, 4);

        when(transactionRepository.getTransactionById(transactionId)).thenReturn(existingTransaction);

        Transactions transactions = transactionService.getTransactionById(transactionId);

        assertEquals(existingTransaction, transactions);

    }

    @Test
    public void testUpdateTransaction() throws SQLException {
        int transactionUpdateId = 11;
        Transactions transactions = new Transactions(11, "Deposit", LocalDate.now(),
                10.0, 9, 2);

        when(transactionRepository.updateTransaction(transactionUpdateId, transactions)).thenReturn(transactions);

        Transactions updatedTransaction = transactionService.updateTransaction(transactionUpdateId, transactions);

        assertEquals(transactions, updatedTransaction);
    }
}
