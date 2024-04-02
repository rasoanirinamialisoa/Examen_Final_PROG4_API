package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Transactions;
import com.devunited.examenfinalprog4.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void getAllTransactions_ReturnsListOfTransactions() throws Exception {
        Transactions transaction1 = new Transactions(1, "Deposit",
                200.00, 1, 4, LocalDateTime.of(2023,1,1,15,20,36),
                LocalDateTime.of(2023, 1, 1, 15,20,36));
        Transactions transaction2 = new Transactions(2, "Withdraw", 50.0, 1, 1,
                LocalDateTime.of(2023,2,15, 9,15,10),
                LocalDateTime.of(2023,2,15,9,15,10));
        List<Transactions> transactionsList = Arrays.asList(transaction1, transaction2);

        when(transactionService.getAllTransactions()).thenReturn(transactionsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Deposit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value("Withdraw"));
    }

    @Test
    void getTransactionById_ReturnsTransaction() throws Exception {

        int transactionId = 1;
        Transactions transaction = new Transactions(transactionId,  "Deposit",
                200.00, 1, 4, LocalDateTime.of(2023,1,1,15,20,36),
                LocalDateTime.of(2023, 1, 1, 15,20,36));

        when(transactionService.getTransactionById(transactionId)).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/{id}", transactionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(transactionId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Deposit"));
    }

    @Test
    void addTransaction_ReturnsAddedTransaction() throws Exception {
        Transactions transactions = new Transactions(1, "Deposit",
                200.00, 1, 4, LocalDateTime.of(2023,1,1,15,20,36),
                LocalDateTime.of(2023, 1, 1, 15,20,36));
        when(transactionService.createTransaction(any(Transactions.class))).thenReturn(transactions);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Deposit\",\"date\":\"2023-06-12\",\"amount\":10.00,\"id_accounts\":1,\"id_category_operation\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactions.getId()));
    }


    @Test
    void updateTransaction_ReturnsUpdatedTransaction() throws Exception {
        int transactionId = 11;
        Transactions updatedTransaction = new Transactions(transactionId, "Deposit",
                200.00, 1, 4, LocalDateTime.of(2023,1,1,15,20,36),
                LocalDateTime.of(2023, 1, 1, 15,20,36));

        when(transactionService.updateTransaction(eq(transactionId), any(Transactions.class)))
                .thenReturn(updatedTransaction);

        mockMvc.perform(put("/api/transactions/{id}", transactionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"type\":\"Deposit\",\"date\":\"" +
                                LocalDate.now() + "\",\"amount\":200.0,\"id_accounts\":1,\"id_category_operation\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedTransaction.getId()));
    }
}
