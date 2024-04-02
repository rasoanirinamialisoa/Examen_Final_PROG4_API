package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.AccountType;

import java.sql.SQLException;
import java.util.List;


public interface AccountTypeService {
    List<AccountType> getAllAccountTypes() throws SQLException;
    AccountType getAccountTypeById(int id) throws SQLException;
    AccountType createAccountType(AccountType accountType) throws SQLException;
    AccountType updateAccountType(int id, AccountType accountType) throws SQLException;

    List<String> getAllAccountTypeNames() throws SQLException;
}

