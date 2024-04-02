package com.devunited.examenfinalprog4.Model;

import com.devunited.examenfinalprog4.model.Accounts;
import org.junit.jupiter.api.Test;
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
        int testTypeId = 1;
        boolean testAllowsOverdraft = true;
        double testOverdraftCreditPercentage = 0.333;
        double testInterestRate7Days = 0.01;
        double testInterestRateAfter7Days = 0.02;

        Accounts accounts = new Accounts();

        accounts.setId(testId);
        accounts.setAccount_number(testAccountNumber);
        accounts.setBalance(testBalance);
        accounts.setUser_id(testIdUsers);
        accounts.setType_id(testTypeId);
        accounts.setAllows_overdraft(testAllowsOverdraft);
        accounts.setOverdraft_credit_percentage(testOverdraftCreditPercentage);
        accounts.setInterest_rate_7_days(testInterestRate7Days);
        accounts.setInterest_rate_after_7_days(testInterestRateAfter7Days);

        assertEquals(testId, accounts.getId());
        assertEquals(testAccountNumber, accounts.getAccount_number());
        assertEquals(testBalance, accounts.getBalance());
        assertEquals(testIdUsers, accounts.getUser_id());
        assertEquals(testTypeId, accounts.getType_id());
        assertEquals(testAllowsOverdraft, accounts.isAllows_overdraft());
        assertEquals(testOverdraftCreditPercentage, accounts.getOverdraft_credit_percentage());
        assertEquals(testInterestRate7Days, accounts.getInterest_rate_7_days());
        assertEquals(testInterestRateAfter7Days, accounts.getInterest_rate_after_7_days());
    }

    @Test
    public void testToString() {
        int testId = 1;
        String testAccountNumber = "0123456789";
        double testBalance = 551000.25;
        int testIdUsers = 10;
        int testTypeId = 1;
        boolean testAllowsOverdraft = true;
        double testOverdraftCreditPercentage = 0.333;
        double testInterestRate7Days = 0.01;
        double testInterestRateAfter7Days = 0.02;

        Accounts accounts = new Accounts(testId, testAccountNumber, testBalance, testIdUsers, testTypeId, testAllowsOverdraft, testOverdraftCreditPercentage, testInterestRate7Days, testInterestRateAfter7Days);

        String expectedToString = "Accounts(id=" + testId + ", account_number=" + testAccountNumber +
                ", balance=" + testBalance + ", id_users=" + testIdUsers +
                ", type_id=" + testTypeId + ", allows_overdraft=" + testAllowsOverdraft +
                ", overdraft_credit_percentage=" + testOverdraftCreditPercentage +
                ", interest_rate_7_days=" + testInterestRate7Days +
                ", interest_rate_after_7_days=" + testInterestRateAfter7Days + ")";

        assertEquals(expectedToString, accounts.toString());
    }
}
