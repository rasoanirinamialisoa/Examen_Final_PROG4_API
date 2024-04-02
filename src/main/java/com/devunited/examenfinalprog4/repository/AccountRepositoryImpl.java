package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.Accounts;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        List<Accounts> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Accounts account = mapResultSetToAccount(resultSet);
                accounts.add(account);
            }
        }
        return accounts;
    }

    @Override
    public Accounts getAccountById(int id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAccount(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Accounts createAccount(Accounts account) throws SQLException {

        String query = "INSERT INTO accounts (account_number, balance, user_id, type_id, allows_overdraft, overdraft_credit_percentage, interest_rate_7_days, interest_rate_after_7_days) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getAccount_number());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setInt(3, account.getUser_id());
            preparedStatement.setInt(4, account.getType_id());
            preparedStatement.setBoolean(5, account.isAllows_overdraft());
            preparedStatement.setDouble(6, account.getOverdraft_credit_percentage());
            preparedStatement.setDouble(7, account.getInterest_rate_7_days());
            preparedStatement.setDouble(8, account.getInterest_rate_after_7_days());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        account.setId(generatedKeys.getInt(1));
                        return account;
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            } else {
                throw new SQLException("Failed to insert account into database.");
            }
        }
    }


    @Override
    public boolean withdrawFromAccount(int accountId, double amount) throws SQLException {
        Accounts account = getAccountById(accountId);
        if (account == null) {
            throw new SQLException("Account not found.");
        }

        double newBalance = account.getBalance() - amount;
        if (!account.isAllows_overdraft() && newBalance < 0) {
            return false;
        }

        String query = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, accountId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }


    @Override
    public Accounts updateAccountBalance(int id, double balance) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    public Accounts updateAccount(int id, Accounts account) throws SQLException {
        String query = "UPDATE accounts SET account_number = ?, balance = ?, user_id = ?, type_id = ?, allows_overdraft = ?, overdraft_credit_percentage = ?, interest_rate_7_days = ?, interest_rate_after_7_days = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getAccount_number());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setInt(3, account.getUser_id());
            preparedStatement.setInt(4, account.getType_id());
            preparedStatement.setBoolean(5, account.isAllows_overdraft());
            preparedStatement.setDouble(6, account.getOverdraft_credit_percentage());
            preparedStatement.setDouble(7, account.getInterest_rate_7_days());
            preparedStatement.setDouble(8, account.getInterest_rate_after_7_days());
            preparedStatement.setInt(9, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return account;
            }
        }
        return null;
    }


    private Accounts mapResultSetToAccount(ResultSet resultSet) throws SQLException {
        Accounts account = new Accounts();
        account.setId(resultSet.getInt(Accounts.ID));
        account.setAccount_number(resultSet.getString(Accounts.ACCOUNT_NUMBER));
        account.setBalance(resultSet.getDouble(Accounts.BALANCE));
        account.setUser_id(resultSet.getInt(Accounts.USER_ID));
        account.setType_id(resultSet.getInt(Accounts.TYPE_ID));
        account.setAllows_overdraft(resultSet.getBoolean(Accounts.ALLOWS_OVERDRAFT));
        account.setOverdraft_credit_percentage(resultSet.getDouble(Accounts.OVERDRAFT_CREDIT_PERCENTAGE));
        account.setInterest_rate_7_days(resultSet.getDouble(Accounts.INTEREST_RATE_7_DAYS));
        account.setInterest_rate_after_7_days(resultSet.getDouble(Accounts.INTEREST_RATE_AFTER_7_DAYS));
        return account;
    }
}
