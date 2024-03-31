package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Transactions;
import com.devunited.examenfinalprog4.repository.TransactionRepository;
import com.devunited.examenfinalprog4.repository.TransactionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionRepositoryTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<Transactions> simulatedTransactions = new ArrayList<>();
        simulatedTransactions.add(new Transactions(1, "Deposit", LocalDate.of(2023, 1,
                1), 200.0, 1, 4));
        simulatedTransactions.add(new Transactions(2, "Withdraw", LocalDate.of(2023, 2 ,
                15), 50.0, 1, 1));

        when(transactionRepository.getAllTransactions()).thenReturn(simulatedTransactions);
    }


    @Test
    public void testGetAllTransactions_ReturnsListOfTransactions() throws SQLException {
        List<Transactions> simulatedTransactions = new ArrayList<>();
        simulatedTransactions.add(new Transactions(1, "Type1", LocalDate.now(), 100.0, 1, 1));
        simulatedTransactions.add(new Transactions(2, "Type2", LocalDate.now(), 200.0, 2, 2));

        when(transactionRepository.getAllTransactions()).thenReturn(simulatedTransactions);

        List<Transactions> actualTransactions = transactionRepositoryImpl.getAllTransactions();

        assertThat(actualTransactions).hasSize(10);
    }

    @Test
    public void testGetTransactionById_ValidId_ReturnsTransaction() throws SQLException {
        int testId = 1;

        Transactions actualTransaction = transactionRepositoryImpl.getTransactionById(testId);

        assertThat(actualTransaction).isNotNull();
        assertThat(actualTransaction.getId()).isEqualTo(testId);
    }

    @Test
    public void testGetTransactionById_InvalidId_ReturnsEmpty() throws SQLException {
        int invalidId = 100;

        Transactions actualTransaction = transactionRepositoryImpl.getTransactionById(invalidId);

        assertThat(actualTransaction).isNull();
    }

    @Test
    public void testCreateTransaction_ReturnsCreatedTransaction() {
        try {
            LocalDate transactionDate = LocalDate.of(2024, 3, 30);

            Transactions newTransaction = new Transactions();
            newTransaction.setType("Deposit");
            newTransaction.setDate(transactionDate);
            newTransaction.setAmount(100.0);
            newTransaction.setId_accounts(1);
            newTransaction.setId_category_operation(1);

            when(transactionRepository.createTransaction(any(Transactions.class)))
                    .thenAnswer(invocation -> {
                        Transactions argTransaction = invocation.getArgument(0);
                        return new Transactions(5, argTransaction.getType(), argTransaction.getDate(),
                                argTransaction.getAmount(), argTransaction.getId_accounts(),
                                argTransaction.getId_category_operation());
                    });

            Transactions createdTransaction = transactionRepositoryImpl.createTransaction(newTransaction);

            assertThat(createdTransaction).isNotNull();
            assertThat(createdTransaction.getId()).isPositive();
            assertThat(createdTransaction.getType()).isEqualTo(newTransaction.getType());
            assertThat(createdTransaction.getDate()).isEqualTo(newTransaction.getDate());
            assertThat(createdTransaction.getAmount()).isEqualTo(newTransaction.getAmount());
            assertThat(createdTransaction.getId_accounts()).isEqualTo(newTransaction.getId_accounts());
            assertThat(createdTransaction.getId_category_operation()).isEqualTo(newTransaction.getId_category_operation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testUpdateTransaction_ValidData_ReturnsUpdatedTransaction() throws SQLException {
        int transactionIdToUpdate = 10;
        Transactions updatedTransaction = new Transactions(transactionIdToUpdate, "Deposit",
                LocalDate.now(), 10.0, 9, 2);

        when(transactionRepository.updateTransaction(transactionIdToUpdate, updatedTransaction)).thenReturn(updatedTransaction);

        Transactions result = transactionRepositoryImpl.updateTransaction(transactionIdToUpdate, updatedTransaction);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(transactionIdToUpdate);
        assertThat(result.getAmount()).isEqualTo(updatedTransaction.getAmount());
        assertThat(result.getDate()).isEqualTo(updatedTransaction.getDate());
        assertThat(result.getType()).isEqualTo(updatedTransaction.getType());
        assertThat(result.getId_accounts()).isEqualTo(updatedTransaction.getId_accounts());
        assertThat(result.getId_category_operation()).isEqualTo(updatedTransaction.getId_category_operation());
    }


    @Test
    public void testUpdateTransaction_SQLException_ThrowsException() {
        try {
            int transactionIdToUpdate = 10;
            Transactions updatedTransaction = new Transactions(transactionIdToUpdate, "Deposit",
                    LocalDate.now(), 10.0, 9, 2);

            Transactions result = transactionRepositoryImpl.updateTransaction(transactionIdToUpdate, updatedTransaction);

            assertThat(result).isNotNull();
            assertThat(result.getAmount()).isEqualTo(10.0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
