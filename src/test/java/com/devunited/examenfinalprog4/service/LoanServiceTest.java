package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Loans;
import com.devunited.examenfinalprog4.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanServiceTest {
    private LoanService loanService;
    private LoanRepository loanRepository;

    @BeforeEach
    public void setUp() {
        loanRepository = mock(LoanRepository.class);
        loanService = new LoanServiceImpl(loanRepository);
    }

    @Test
    public void testGetAllLoans_Success() throws SQLException {

        List<Loans> expectedLoans = new ArrayList<>();
        when(loanRepository.getAllLoans()).thenReturn(expectedLoans);

        List<Loans> actualLoans = loanService.getAllLoans();

        assertEquals(expectedLoans, actualLoans);
    }

    @Test
    public void testGetLoanById_Success() throws SQLException {

        int loansId = 5;
        Loans expectedLoan = new Loans(5,3000.00, 6.0f,
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023,5,1, 15, 12, 4),
                LocalDateTime.of(2023, 5, 1, 15, 12, 4), 1);
        when(loanRepository.getLoanById(loansId)).thenReturn(expectedLoan);

        Loans actualLoan = loanService.getLoanById(loansId);

        assertEquals(expectedLoan, actualLoan);
    }

    @Test
    public void testCreateLoan_Success() throws SQLException {

        Loans loans = new Loans(6, 7000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45), 10);
        when(loanRepository.createLoan(loans)).thenReturn(loans);

        Loans createdLoan = loanService.createLoan(loans);

        assertEquals(loans, createdLoan);
    }

    @Test
    public void testUpdateLoan_Success() throws SQLException {

        int loansId = 6;
        Loans loan = new Loans(1, 70000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45), 10);
        when(loanRepository.updateLoan(loansId, loan)).thenReturn(loan);

        Loans updatedLoan = loanService.updateLoan(loansId, loan);

        assertEquals(loan, updatedLoan);
    }
}
