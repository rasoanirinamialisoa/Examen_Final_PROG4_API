package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.AccountType;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface AccountTypeRepository {
    List<AccountType> getAllAccountTypes() throws SQLException;
    AccountType getAccountTypeById(int id) throws SQLException;
    AccountType createAccountType(AccountType accountType) throws SQLException;
    AccountType updateAccountType(int id, AccountType accountType) throws SQLException;
    int getAccountTypeIdByName(String typeName) throws SQLException;

    List<String> getAllAccountTypeNames() throws SQLException;
}
