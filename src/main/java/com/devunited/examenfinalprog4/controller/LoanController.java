package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Loans;
import com.devunited.examenfinalprog4.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loans")
    public List<Loans> getAllLoans() throws SQLException {
        return loanService.getAllLoans();
    }

    @GetMapping("/loans/{id}")
    public Loans getLoanById(@PathVariable int id) throws SQLException {
        return loanService.getLoanById(id);
    }

    @PostMapping("/loans")
    public Loans addLoan(@RequestBody Loans loan) throws SQLException {
        return loanService.createLoan(loan);
    }

    @PutMapping("/loans/{id}")
    public Loans updateLoan(@PathVariable int id, @RequestBody Loans loan) throws SQLException {
        return loanService.updateLoan(id, loan);
    }
}
