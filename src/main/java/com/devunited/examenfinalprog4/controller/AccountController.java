package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.repository.AccountTypeRepositoryImpl;
import com.devunited.examenfinalprog4.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    private final AccountTypeRepositoryImpl accountTypeRepositoryImpl;

    public AccountController(AccountService accountService, AccountTypeRepositoryImpl accountTypeRepositoryImpl) {
        this.accountService = accountService;
        this.accountTypeRepositoryImpl = accountTypeRepositoryImpl;
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

    @PostMapping("/accounts/withdraw/{typeName}")
    public ResponseEntity<?> withdrawFromAccount(@PathVariable String typeName, @RequestBody Map<String, Object> withdrawalMap) {
        Number balanceNumber = (Number) withdrawalMap.getOrDefault("balance", 0.0);
        double balance = balanceNumber.doubleValue();
        // Récupérer l'accountId du corps de la requête
        Number accountIdNumber = (Number) withdrawalMap.getOrDefault("id", 1);
        int id = accountIdNumber.intValue();

        if (balance <= 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount must be positive.");
        }

        try {
            // Récupérer l'ID du type de compte à partir du nom
            int accountTypeId = accountTypeRepositoryImpl.getAccountTypeIdByName(typeName);

            boolean result = accountService.withdraw(id, balance, accountTypeId); // Utilisation de accountId récupéré
            if (result) {
                return ResponseEntity.ok().body("Withdrawal successful.");
            } else {
                return ResponseEntity.badRequest().body("Withdrawal not authorized due to insufficient funds or overdraft rules.");
            }
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @PutMapping("/accounts/{id}/updateBalance")
    public ResponseEntity<String> updateAccountBalance(@PathVariable int accountId, @RequestParam double amount) {
        try {
            accountService.updateAccountBalance(accountId, amount);
            return ResponseEntity.ok("Account balance updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating account balance");
        }
    }

}
