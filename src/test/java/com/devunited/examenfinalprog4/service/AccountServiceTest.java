package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AccountServiceTest {
    private AccountService accountService;
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void testGetAllAccounts_Success() throws SQLException {

        List<Accounts> expectedAccounts = new ArrayList<>();
        when(accountRepository.getAllAccounts()).thenReturn(expectedAccounts);

        List<Accounts> actualAccounts = accountService.getAllAccounts();

        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    public void testGetAccountById_Success() throws SQLException {

        int accountId = 1;
        Accounts expectedAccount = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountRepository.getAccountById(accountId)).thenReturn(expectedAccount);

        Accounts actualAccount = accountService.getAccountById(accountId);

        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testCreateAccount_Success() throws SQLException {

        Accounts account = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountRepository.createAccount(account)).thenReturn(account);

        Accounts createdAccount = accountService.createAccount(account);

        assertEquals(account, createdAccount);
    }

    @Test
    public void testUpdateAccount_Success() throws SQLException {

        int accountId = 1;
        Accounts account = new Accounts(1, "1234567890", 100000.00, 1);
        when(accountRepository.updateAccount(accountId, account)).thenReturn(account);

        Accounts updatedAccount = accountService.updateAccount(accountId, account);

        assertEquals(account, updatedAccount);
    }
}
