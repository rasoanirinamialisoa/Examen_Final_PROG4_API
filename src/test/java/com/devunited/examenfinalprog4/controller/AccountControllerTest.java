package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testGetAllAccounts_Success() throws Exception {

        List<Accounts> accounts = new ArrayList<>();
        when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAccountById_Success() throws Exception {

        int accountId = 1;
        Accounts account = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountService.getAccountById(accountId)).thenReturn(account);

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(account.getId()));
    }

    @Test
    public void testAddAccount_Success() throws Exception {

        Accounts account = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountService.createAccount(any(Accounts.class))).thenReturn(account);

        mockMvc.perform(post("/api/accounts")
                        .contentType("application/json")
                        .content("{\"account_number\":\"1234567890\",\"balance\":100000.00,\"id_users\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(account.getId()));
    }

    @Test
    public void testUpdateAccount_Success() throws Exception {

        int accountId = 1;
        Accounts account = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountService.updateAccount(eq(accountId), any(Accounts.class))).thenReturn(account);

        mockMvc.perform(put("/api/accounts/{id}", accountId)
                        .contentType("application/json")
                        .content("{\"account_number\":\"12345678910\",\"balance\":100000.00,\"id_users\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(account.getId()));
    }
}
