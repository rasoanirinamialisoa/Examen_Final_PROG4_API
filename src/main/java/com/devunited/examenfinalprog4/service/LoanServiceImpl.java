package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Loans;
import com.devunited.examenfinalprog4.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public List<Loans> getAllLoans() throws SQLException {
        return loanRepository.getAllLoans();
    }

    @Override
    public Loans getLoanById(int id) throws SQLException {
        return loanRepository.getLoanById(id);
    }

    @Override
    public Loans createLoan(Loans loan) throws SQLException {
        return loanRepository.createLoan(loan);
    }

    @Override
    public Loans updateLoan(int id, Loans loan) throws SQLException {
        return loanRepository.updateLoan(id, loan);
    }
}
