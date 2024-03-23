package com.devunited.examenfinalprog4.Model;

import com.devunited.examenfinalprog4.model.Accounts;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountModelTest {
    @Test
    public void testGettersAndSetters() {
        int testId = 1;
        String testAccountNumber = "1234567890";
        double testBalance = 1000.00;
        int testIdUsers = 1;

        Accounts accounts = new Accounts();

        accounts.setId(testId);
        accounts.setAccount_number(testAccountNumber);
        accounts.setBalance(testBalance);
        accounts.setId(testIdUsers);

        assertEquals(testId, accounts.getId());
        assertEquals(testAccountNumber, accounts.getAccount_number());
        assertEquals(testBalance, accounts.getBalance());
        assertEquals(testIdUsers, accounts.getId());
    }

    @Test
    public void testToString() {
        int testId = 1;
        String testAccountNumber = "0123456789";
        double testBalance = 551000.25;
        int testIdUsers = 10;

        Accounts accounts = new Accounts(testId, testAccountNumber, testBalance, testIdUsers);

        String expectedToString = "Accounts(id=" + testId + ", account_number=" + testAccountNumber +
                ", balance=" + testBalance + ", id_users=" + testIdUsers + ")";

        assertEquals(expectedToString, accounts.toString());
    }
}
