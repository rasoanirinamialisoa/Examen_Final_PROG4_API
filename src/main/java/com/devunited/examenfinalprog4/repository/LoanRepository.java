package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.Loans;

import java.sql.SQLException;
import java.util.List;

public interface LoanRepository {
    List<Loans> getAllLoans() throws SQLException;
    Loans getLoanById(int id) throws SQLException;
    Loans createLoan(Loans loan) throws SQLException;
    Loans updateLoan(int id, Loans loan) throws SQLException;
}
