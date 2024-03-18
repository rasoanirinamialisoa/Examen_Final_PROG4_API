package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Accounts;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    List<Accounts> getAllAccounts() throws SQLException;
    Accounts getAccountById(int id) throws SQLException;
    Accounts createAccount(Accounts account) throws SQLException;
    Accounts updateAccount(int id, Accounts account) throws SQLException;

}
