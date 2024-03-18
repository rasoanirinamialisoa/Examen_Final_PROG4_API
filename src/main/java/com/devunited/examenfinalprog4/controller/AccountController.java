package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Accounts getAccountById(@PathVariable int id) throws SQLException {
        return accountService.getAccountById(id);
    }

    @PostMapping("/accounts")
    public Accounts addAccount(@RequestBody Accounts account) throws SQLException {
        return accountService.createAccount(account);
    }

    @PutMapping("/accounts/{id}")
    public Accounts updateAccount(@PathVariable int id, @RequestBody Accounts account) throws SQLException {
        return accountService.updateAccount(id, account);
    }
}
