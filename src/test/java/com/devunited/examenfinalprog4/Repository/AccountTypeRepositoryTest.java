package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.AccountType;
import com.devunited.examenfinalprog4.repository.AccountTypeRepository;
import com.devunited.examenfinalprog4.repository.AccountTypeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountTypeRepositoryTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @InjectMocks
    private  AccountTypeRepositoryImpl accountTypeRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<AccountType> simulatedAccountTypes = new ArrayList<>();
        simulatedAccountTypes.add(new AccountType(1, "Checking Account"));
        simulatedAccountTypes.add(new AccountType(2, "Savings Account"));

        when(accountTypeRepository.getAllAccountTypes()).thenReturn(simulatedAccountTypes);
        when(accountTypeRepository.getAccountTypeById(1)).thenReturn(new AccountType(1, "Checking Account"));
        when(accountTypeRepository.createAccountType(any(AccountType.class))).thenReturn(new AccountType(3, "Type3"));
        when(accountTypeRepository.updateAccountType(eq(2), any(AccountType.class))).thenReturn(new AccountType(2, "Type2Updated"));
        when(accountTypeRepository.getAllAccountTypeNames())
                .thenReturn(List.of("Checking Account", "Saving Account", "Investment Account"));
    }

    @Test
    public void testGetAllAccountTypes() throws SQLException {
        List<AccountType> result = accountTypeRepositoryImpl.getAllAccountTypes();

        assertEquals(2, result.size());
        assertEquals("Checking Account", result.get(0).getName());
        assertEquals("Saving Account", result.get(1).getName());

        verify(accountTypeRepository, times(1)).getAllAccountTypes();
    }

    @Test
    public void testGetAccountTypeById() throws SQLException {
        AccountType result = accountTypeRepositoryImpl.getAccountTypeById(1);

        assertEquals("Checking Account", result.getName());

        verify(accountTypeRepository, times(1)).getAccountTypeById(1);
    }

    @Test
    public void testCreateAccountType() throws SQLException {
        AccountType accountType = new AccountType(3, "Investment Account");
        AccountType result = accountTypeRepositoryImpl.createAccountType(accountType);

        assertEquals("Investment Account", result.getName());

        verify(accountTypeRepository, times(1)).createAccountType(accountType);
    }

    @Test
    public void testUpdateAccountType() throws SQLException {
        AccountType accountType = new AccountType(2, "Saving Account update");
        AccountType result = accountTypeRepositoryImpl.updateAccountType(2, accountType);

        assertEquals("Saving Account Update", result.getName());

        verify(accountTypeRepository, times(1)).updateAccountType(2, accountType);
    }

    @Test
    public void testGetAllAccountTypeNames() throws SQLException {
        List<String> result = accountTypeRepositoryImpl.getAllAccountTypeNames();

        assertEquals(3, result.size());
        assertEquals("Checking Account", result.get(0));
        assertEquals("Saving Account", result.get(1));
        assertEquals("Investment Account", result.get(2));

        verify(accountTypeRepository, times(1)).getAllAccountTypeNames();
    }
}
