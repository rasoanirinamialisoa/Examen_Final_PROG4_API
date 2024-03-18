package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.Accounts;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface AccountRepository {
    List<Accounts> getAllAccounts() throws SQLException;
    Accounts getAccountById(int id) throws SQLException;
    Accounts createAccount(Accounts account) throws SQLException;
    Accounts updateAccount(int id, Accounts account) throws SQLException;
}
