package com.devunited.examenfinalprog4.controller;


import com.devunited.examenfinalprog4.model.AccountType;
import com.devunited.examenfinalprog4.service.AccountTypeService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping("/account-types")
    public List<AccountType> getAllAccountTypes() throws SQLException {
        return accountTypeService.getAllAccountTypes();
    }

    @GetMapping("/account-types/names")
    public List<String> getAllAccountTypeNames() throws SQLException {
        return accountTypeService.getAllAccountTypeNames();
    }



    @GetMapping("/account-types/{id}")
    public AccountType getAccountTypeById(@PathVariable int id) throws SQLException {
        return accountTypeService.getAccountTypeById(id);
    }

    @PostMapping("/account-types")
    public AccountType createAccountType(@RequestBody AccountType accountType) throws SQLException {
        return accountTypeService.createAccountType(accountType);
    }

    @PutMapping("/account-types/{id}")
    public AccountType updateAccountType(@PathVariable int id, @RequestBody AccountType accountType) throws SQLException {
        return accountTypeService.updateAccountType(id, accountType);
    }

}

