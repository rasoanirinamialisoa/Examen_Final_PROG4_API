package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/accounts/withdraw/{id}")
    public ResponseEntity<?> withdrawFromAccount(@PathVariable int id, @RequestBody Map<String, Double> withdrawalMap) {
        double amount = withdrawalMap.getOrDefault("amount", 0.0);
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount must be positive.");
        }

        try {
            boolean result = accountService.withdraw(id, amount);
            if (result) {
                return ResponseEntity.ok().body("Withdrawal successful.");
            } else {

                return ResponseEntity.badRequest().body("Withdrawal not authorized due to insufficient funds or overdraft rules.");
            }
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



}
