package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.repository.AccountRepository;
import com.devunited.examenfinalprog4.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountRepositoryTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountRepositoryImpl accountRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<Accounts> simulatedAccounts = new ArrayList<>();
        simulatedAccounts.add(new Accounts(1, "1234567890", 1500.00, 1, 1, true, 0.333, 0.01, 0.02));
        simulatedAccounts.add(new Accounts(2, "2345678901", 25000.00, 1, 2, false, 0.333, 0.01, 0.02));
        simulatedAccounts.add(new Accounts(3, "3456789012", 50000.00, 1, 3, true, 0.333, 0.01, 0.02));

        when(accountRepository.getAllAccounts()).thenReturn(simulatedAccounts);
    }

    @Test
    public void testGetAllAccounts_ReturnsListOfAccounts() throws SQLException {
        List<Accounts> actualAccounts = accountRepositoryImpl.getAllAccounts();

        assertThat(actualAccounts).hasSize(3);
    }

    @Test
    public void testGetAccountById_ValidId_ReturnsAccount() throws SQLException {
        int testId = 2;

        Accounts actualAccount = accountRepositoryImpl.getAccountById(testId);

        assertThat(actualAccount).isNotNull();
        assertThat(actualAccount.getId()).isEqualTo(testId);
    }

    @Test
    public void testGetAccountById_InvalidId_ReturnsNull() throws SQLException {
        int invalidId = 100;

        Accounts actualAccount = accountRepositoryImpl.getAccountById(invalidId);

        assertThat(actualAccount).isNull();
    }

    @Test
    public void testCreateAccount_ValidAccount_ReturnsCreatedAccount() {
        Accounts newAccount = new Accounts();
        try {
            when(accountRepository.createAccount(any(Accounts.class)))
                    .thenReturn(new Accounts(1, newAccount.getAccount_number(), newAccount.getBalance(),
                            newAccount.getUser_id(), newAccount.getType_id(), newAccount.isAllows_overdraft(),
                            newAccount.getOverdraft_credit_percentage(), newAccount.getInterest_rate_7_days(),
                            newAccount.getInterest_rate_after_7_days()));

            Accounts createdAccount = accountRepositoryImpl.createAccount(newAccount);

            assertThat(createdAccount).isNotNull();
            assertThat(createdAccount.getId()).isEqualTo(1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred during test execution");
        }
    }

    @Test
    public void testUpdateAccount_ValidData_ReturnsUpdatedAccount() throws SQLException {
        int accountIdToUpdate = 3;
        Accounts updatedAccount = new Accounts(accountIdToUpdate, "3456789012", 60000.00, 1, 3, true, 0.333, 0.01, 0.02);

        when(accountRepository.updateAccount(accountIdToUpdate, updatedAccount)).thenReturn(updatedAccount);

        try {
            Accounts result = accountRepositoryImpl.updateAccount(accountIdToUpdate, updatedAccount);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(accountIdToUpdate);
            assertThat(result.getBalance()).isEqualTo(60000.00);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred during test execution");
        }
    }
}
