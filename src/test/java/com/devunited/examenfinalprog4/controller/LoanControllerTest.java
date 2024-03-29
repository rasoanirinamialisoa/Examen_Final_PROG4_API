package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Loans;
import com.devunited.examenfinalprog4.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Test
    public void testGetAllLoans_Success() throws Exception {

        List<Loans> loans = new ArrayList<>();
        loans.add(new Loans(1, 7000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                1));
        loans.add(new Loans(2, 1500.00, 6.0f,
                LocalDate.of(2023, 2, 1 ),
                LocalDate.of(2023, 12, 31), "Active",
                LocalDateTime.of(2023, 2, 1, 11, 10, 35),
                LocalDateTime.of(2023, 2, 1, 11, 10, 35),
                2));

        when(loanService.getAllLoans()).thenReturn(loans);

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].amount").value(7000.00))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].amount").value(1500.00));
    }

    @Test
    public void testGetLoanById_Success() throws Exception {

        int loanId = 11;
        Loans loans = new Loans(1, 7000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                1);

        when(loanService.getLoanById(loanId)).thenReturn(loans);

        mockMvc.perform(get("/api/loans/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.amount").value(7000.00));
    }

    @Test
    public void testAddLoan_Success() throws Exception {

        Loans loans = new Loans(6, 7000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45), 10);

        when(loanService.createLoan(any(Loans.class))).thenReturn(loans);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":6,\"amount\":\7000.00}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.amount").value(7000.00));
    }

    @Test
    public void testUpdateLoan_Success() throws Exception {

        int loanId = 6;
        Loans loans = new Loans(6, 70000.00, 5.5f,
                LocalDate.of(2024, 3, 28 ),
                LocalDate.of(2025, 2, 28), "Active",
                LocalDateTime.of(2024, 3, 28, 9, 30, 45),
                LocalDateTime.of(2024, 3, 28, 9, 30, 45), 10);

        when(loanService.updateLoan(eq(loanId), any(Loans.class))).thenReturn(loans);

        mockMvc.perform(put("/api/loans/{id}", loanId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":6,\"amount\":\70000.00}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.amount").value(70000.00));
    }
}
