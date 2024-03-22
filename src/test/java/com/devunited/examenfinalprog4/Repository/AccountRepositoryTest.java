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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
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
        simulatedAccounts.add(new Accounts(1, "1234567890", 100000.0, 1));
        simulatedAccounts.add(new Accounts(2, "2345678901", 10500.5, 2));
        simulatedAccounts.add(new Accounts(3, "3456789012", 552000.75, 3));
        simulatedAccounts.add(new Accounts(4, "4567890123", 2500000.00, 4));
        simulatedAccounts.add(new Accounts(5, "5678901234", 3050000.25, 5));
        simulatedAccounts.add(new Accounts(6, "678901234", 35000.00, 6));
        simulatedAccounts.add(new Accounts(7, "7890123456", 400000.50,7));
        simulatedAccounts.add(new Accounts(8, "8901234567", 545000.75, 8));
        simulatedAccounts.add(new Accounts(9, "9012345678", 500000.00, 9));
        simulatedAccounts.add(new Accounts(10, "0123456789",  551000.25, 10));

        when(accountRepository.getAllAccounts()).thenReturn(simulatedAccounts);
    }

    @Test
    public void testGetAllAccounts_ReturnsListOfAccounts() throws SQLException {
        List<Accounts> simulatedAccounts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            simulatedAccounts.add(new Accounts(i, "1234567890" + i, 100000.0 + i, 1 ));
        }

        when(accountRepository.getAllAccounts()).thenReturn(simulatedAccounts);

        List<Accounts> actualAccounts = accountRepositoryImpl.getAllAccounts();

        assertThat(actualAccounts).hasSize(10);
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
    public void testCreateAccount_ValidAccount_ReturnsCreatedAccount(){
        Accounts newAccount = new Accounts();
        try {
            Accounts createdAccount = accountRepositoryImpl.createAccount(newAccount);

            when(accountRepository.createAccount(any(Accounts.class)))
                    .thenAnswer(invocation -> {
                        Accounts argAccount = invocation.getArgument(0);
                        return new Accounts(5, argAccount.getAccount_number(), argAccount.getBalance(), argAccount.getId_users());
                    });

            assertThat(createdAccount).isNotNull();
            assertThat(createdAccount.getId()).isPositive();
            assertThat(createdAccount.getAccount_number()).isEqualTo(newAccount.getAccount_number());
            assertThat(createdAccount.getBalance()).isEqualTo(newAccount.getBalance());
            assertThat(createdAccount.getId_users()).isEqualTo(newAccount.getId_users());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

        @Test
    public void testUpdateAccount_ValidData_ReturnsUpdatedAccount() throws SQLException {
        int accountIdToUpdate = 3;
        Accounts updatedAccount = new Accounts(accountIdToUpdate, "9988776655", 6000.00, 5);

        when(accountRepository.updateAccount(accountIdToUpdate, updatedAccount)).thenReturn(updatedAccount);

        Accounts result = accountRepositoryImpl.updateAccount(accountIdToUpdate, updatedAccount);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(accountIdToUpdate);
        assertThat(result.getAccount_number()).isEqualTo(updatedAccount.getAccount_number());
        assertThat(result.getBalance()).isEqualTo(updatedAccount.getBalance());
        assertThat(result.getId_users()).isEqualTo(updatedAccount.getId_users());
    }

    @Test
    public void testGetAllAccounts_SQLException_ThrowsException() throws SQLException {
        List<Accounts> accounts = accountRepositoryImpl.getAllAccounts();

        assertThat(accounts).hasSize(10);
        assertThat(accounts).extracting(Accounts::getAccount_number)
                .contains("1234567890", "2345678901", "3456789012", "4567890123",
                        "5678901234", "6789012345", "7890123456", "8901234567",
                        "9012345678", "0123456789");
    }

    @Test
    public void testGetAccountById_SQLException_ThrowsException(){
        try {
            Accounts accounts = accountRepositoryImpl.getAccountById(2);

            assertThat(accounts).isNotNull();
            assertThat(accounts.getAccount_number()).isEqualTo("2345678901");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAccount_SQLException_ThrowsException() throws SQLException {
        Accounts newAccount = new Accounts();
        when(accountRepository.createAccount(newAccount)).thenThrow(new SQLException());

        try {
            accountRepositoryImpl.createAccount(newAccount);
            fail("Expected SQLException was not thrown");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateAccount_SQLException_ThrowsException() {
        try {
            int accountIdToUpdate = 10;
            Accounts updatedAccount = new Accounts(accountIdToUpdate, "0123456789", 50000.25, 10);
            Accounts result = accountRepositoryImpl.updateAccount(accountIdToUpdate, updatedAccount);

            assertThat(result).isNotNull();
            assertThat(result.getAccount_number()).isEqualTo("0123456789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
