package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Loans;
import com.devunited.examenfinalprog4.repository.LoanRepository;
import com.devunited.examenfinalprog4.repository.LoanRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanRepositoryTest {
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanRepositoryImpl loanRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<Loans> simulatedLoans = new ArrayList<>();
        simulatedLoans.add(new Loans(1, 1000.00, 5.5f,
                LocalDate.of(2023, 1, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 1, 1, 11, 0, 5),
                LocalDateTime.of(2023, 1, 1, 11, 0, 5),
                1));
        simulatedLoans.add(new Loans(2, 1500.00, 6.0f,
                LocalDate.of(2023, 2, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 2, 1, 9, 30, 45),
                LocalDateTime.of(2023, 2, 1, 9, 30, 45),
                2));
        simulatedLoans.add(new Loans(3, 2000.00, 5.0f,
                LocalDate.of(2023, 3, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 3, 1, 10, 30, 45),
                LocalDateTime.of(2023, 3, 1, 10, 30, 45),
                14));
        simulatedLoans.add(new Loans(4, 2500.00, 5.5f,
                LocalDate.of(2023, 4, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 4, 1, 14, 50, 4),
                LocalDateTime.of(2023, 4, 1, 14, 50, 4),
                4));
        simulatedLoans.add(new Loans(5, 3000.00, 6.0f,
                LocalDate.of(2023, 5, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 5, 1, 16, 50, 2),
                LocalDateTime.of(2023, 5, 1, 16, 50, 2),
                5));

        when(loanRepository.getAllLoans()).thenReturn(simulatedLoans);
    }

    @Test
    public void testGetAllLoans_ReturnsListOfLoans() throws SQLException {
        List<Loans> simulatedLoan = new ArrayList<>();
        LocalDateTime baseDateTime = LocalDateTime.of(2023, 5, 1, 16, 50, 2);

        for (int i = 1; i <= 5; i++) {
            simulatedLoan.add(new Loans(i, 3000.00 + i, 6.0f + i,
                    LocalDate.parse("2023-05-01").plusDays(i),
                    LocalDate.parse("2023-12-31").plusDays(i),
                    "Active" + i, baseDateTime.plusSeconds(i),
                    baseDateTime.plusSeconds(i), 5));
        }

        when(loanRepository.getAllLoans()).thenReturn(simulatedLoan);

        List<Loans> actualLoan = loanRepositoryImpl.getAllLoans();

        assertThat(actualLoan).hasSize(20);
    }


    @Test
    public void testGetLoanById_ValidId_ReturnsLoan() throws SQLException {
        int loanId = 20;

        Loans actualLoan = loanRepositoryImpl.getLoanById(loanId);

        assertThat(actualLoan).isNotNull();

        assertThat(actualLoan.getId()).isEqualTo(loanId);
    }


    @Test
    public void testGetLoanById_InvalidId_ReturnsNull() throws SQLException {
        int invalidId = 100;

        Loans actualLoan = loanRepositoryImpl.getLoanById(invalidId);

        assertThat(actualLoan).isNull();
    }

    @Test
    public void testCreateLoan_ReturnsCreatedLoan() {
        try {
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 12, 31);
            LocalDateTime creationDate = LocalDateTime.now();
            LocalDateTime updateDate = LocalDateTime.now();

            Loans newLoan = new Loans();
            newLoan.setAmount(1000.00);
            newLoan.setInterest_rate(5.5f);
            newLoan.setStart_date(startDate);
            newLoan.setEnd_date(endDate);
            newLoan.setStatus("Active");
            newLoan.setCreation_date(creationDate);
            newLoan.setUpdate_date(updateDate);
            newLoan.setId_accounts(1);

            Loans createdLoan = loanRepositoryImpl.createLoan(newLoan);

            when(loanRepository.createLoan(any(Loans.class)))
                    .thenAnswer(invocation -> {
                        Loans argLoan = invocation.getArgument(0);
                        return new Loans(5, argLoan.getAmount(), argLoan.getInterest_rate(),
                                argLoan.getStart_date(), argLoan.getEnd_date(), argLoan.getStatus(),
                                argLoan.getCreation_date(), argLoan.getUpdate_date(), argLoan.getId_accounts());
                    });

            assertThat(createdLoan).isNotNull();
            assertThat(createdLoan.getId()).isPositive();
            assertThat(createdLoan.getAmount()).isEqualTo(newLoan.getAmount());
            assertThat(createdLoan.getInterest_rate()).isEqualTo(newLoan.getInterest_rate());
            assertThat(createdLoan.getStart_date()).isEqualTo(newLoan.getStart_date());
            assertThat(createdLoan.getEnd_date()).isEqualTo(newLoan.getEnd_date());
            assertThat(createdLoan.getStatus()).isEqualTo(newLoan.getStatus());
            assertThat(createdLoan.getCreation_date()).isEqualTo(newLoan.getCreation_date());
            assertThat(createdLoan.getUpdate_date()).isEqualTo(newLoan.getUpdate_date());
            assertThat(createdLoan.getId_accounts()).isEqualTo(newLoan.getId_accounts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateLoan_ValidData_ReturnsUpdatedLoan() throws SQLException {
        int loanIdUpdate = 20;
        Loans updatedLoan = new Loans(loanIdUpdate, 300.00, 6.0f,
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 5, 1, 16, 50, 2),
                LocalDateTime.of(2024, 3, 29, 14, 39, 16),
                5);

        when(loanRepository.updateLoan(eq(loanIdUpdate), eq(updatedLoan))).thenReturn(updatedLoan);

        Loans result = loanRepositoryImpl.updateLoan(loanIdUpdate, updatedLoan);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(loanIdUpdate);
        assertThat(result.getAmount()).isEqualTo(updatedLoan.getAmount());
        assertThat(result.getInterest_rate()).isEqualTo(updatedLoan.getInterest_rate());
        assertThat(result.getStart_date()).isEqualTo(updatedLoan.getStart_date());
        assertThat(result.getEnd_date()).isEqualTo(updatedLoan.getEnd_date());
        assertThat(result.getStatus()).isEqualTo(updatedLoan.getStatus());
        assertThat(result.getCreation_date()).isEqualTo(updatedLoan.getCreation_date());
        assertThat(result.getUpdate_date()).isEqualTo(updatedLoan.getUpdate_date());
        assertThat(result.getId_accounts()).isEqualTo(updatedLoan.getId_accounts());
    }


    @Test
    public void testGetAllLoans_SQLException_ThrowsException() throws SQLException {
        List<Loans> loans = loanRepositoryImpl.getAllLoans();

        assertThat(loans).hasSize(20);
        assertThat(loans).extracting(Loans::getId)
                .contains(6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    }



    @Test
    public void testUpdateLoan_SQLException_ThrowsException() {
        try {
            int loanIdUpdate = 5;
            Loans updatedLoan = new Loans(loanIdUpdate, 300.00, 6.0f,
                    LocalDate.of(2023, 5, 1),
                    LocalDate.of(2023, 12, 31), "Active",
                    LocalDateTime.of(2023, 5, 1, 16, 50, 2),
                    LocalDateTime.of(2024, 3, 29, 14, 39, 16),
                    5);

            when(loanRepository.updateLoan(eq(loanIdUpdate), eq(updatedLoan))).thenThrow(new SQLException());
            Loans result = loanRepositoryImpl.updateLoan(loanIdUpdate, updatedLoan);

            assertThat(result).isNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
