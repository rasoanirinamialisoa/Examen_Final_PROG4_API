package com.devunited.examenfinalprog4.service;


import com.devunited.examenfinalprog4.model.AccountType;
import com.devunited.examenfinalprog4.repository.AccountTypeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountType> getAllAccountTypes() throws SQLException {
        return accountTypeRepository.getAllAccountTypes();
    }
    @Override
    public AccountType getAccountTypeById(int id) throws SQLException {
        return accountTypeRepository.getAccountTypeById(id);
    }

    @Override
    public AccountType createAccountType(AccountType accountType) throws SQLException {
        return accountTypeRepository.createAccountType(accountType);
    }

    @Override
    public AccountType updateAccountType(int id, AccountType accountType) throws SQLException {
        return accountTypeRepository.updateAccountType(id, accountType);
    }

    @Override
    public List<String> getAllAccountTypeNames() throws SQLException {
        return accountTypeRepository.getAllAccountTypeNames();
    }


}
