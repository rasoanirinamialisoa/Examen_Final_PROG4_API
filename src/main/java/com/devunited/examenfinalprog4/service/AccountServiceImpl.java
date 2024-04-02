package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Accounts;
import com.devunited.examenfinalprog4.repository.AccountRepository;
import com.devunited.examenfinalprog4.model.AccountType;
import com.devunited.examenfinalprog4.repository.AccountTypeRepositoryImpl;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devunited.examenfinalprog4.model.Users;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final AccountTypeRepositoryImpl accountTypeRepositoryImpl;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepositoryImpl userRepositoryImpl, AccountTypeRepositoryImpl accountTypeRepositoryImpl) {
        this.accountRepository = accountRepository;
        this.userRepositoryImpl = userRepositoryImpl;
        this.accountTypeRepositoryImpl = accountTypeRepositoryImpl;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountRepository.getAllAccounts();
    }

    @Override
    public Accounts getAccountById(int id) throws SQLException {
        return accountRepository.getAccountById(id);
    }

    @Override
    public Accounts createAccount(Accounts account) throws SQLException {
        return accountRepository.createAccount(account);
    }

    @Override
    public Accounts updateAccount(int id, Accounts account) throws SQLException {
        return accountRepository.updateAccount(id, account);
    }

    public boolean withdraw(int id, double amount, int accountTypeId) throws SQLException {
        // Récupérer le compte par ID
        Accounts account = accountRepository.getAccountById(id);
        if (account == null) {
            throw new SQLException("Account not found.");
        }

        // Récupérer le type de compte associé à l'ID du type de compte
        AccountType accountType = accountTypeRepositoryImpl.getAccountTypeById(accountTypeId);
        if (accountType == null) {
            throw new SQLException("Account type not found");
        }

        //Récupérer l'utilisateur associé au compte
        Users user = userRepositoryImpl.getUserById(account.getUser_id());
        if (user == null) {
            throw new SQLException("User information not available.");
        }

        // Calculer le crédit autorisé en fonction des règles du type de compte
        double creditLimit = 0.0;
        if (accountType.getName().equals("checking")) {
            // Si c'est un compte chèque, le crédit autorisé est un tiers du salaire mensuel net de l'utilisateur
            creditLimit = user.getMonthly_salary() / 3;
        } else if (accountType.getName().equals("savings")) {
            // Si c'est un compte épargne, le crédit autorisé est fixé à un montant spécifique, par exemple 1000
            creditLimit = 1000.0;
        }

        // Calculer le solde après le retrait
        double newBalance = account.getBalance() - amount;

        // Vérifier si le retrait est autorisé
        if (newBalance < 0 && !account.isAllows_overdraft()) {
            // Vérifier si le découvert est autorisé pour ce compte
            if (newBalance < -creditLimit) {
                // Le solde après le retrait dépasse le crédit autorisé, donc le retrait n'est pas autorisé
                return false;
            }
        }
        // Mettre à jour le solde du compte
        account.setBalance(newBalance);
        accountRepository.updateAccount(id, account);
        return true;
    }

    @Override
    public Accounts updateAccountBalance(int id, double balance) throws SQLException {
        try {
            // Récupérer le compte à partir de l'ID
            Optional<Accounts> optionalAccount = Optional.ofNullable(accountRepository.getAccountById(id));
            if (optionalAccount.isPresent()) {
                Accounts account = optionalAccount.get();

                // Récupérer le solde actuel
                double currentBalance = account.getBalance();

                // Calculer le nouveau solde en ajoutant le montant spécifié
                double updatedBalance = currentBalance + balance;

                // Mettre à jour le solde dans l'objet Account
                account.setBalance(updatedBalance);

                // Mettre à jour le compte dans la base de données
                return accountRepository.updateAccountBalance(id, balance);
            } else {
                throw new RuntimeException("Account not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error to update the accounts", e);
        }
    }

}