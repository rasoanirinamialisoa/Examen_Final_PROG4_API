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

        String query = "INSERT INTO accounts (account_number, balance, id_users) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getAccount_number());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setInt(3, account.getId_users());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        account.setId(generatedKeys.getInt(1));
                        return account;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion de compte dans la base de données.");
            }
        }
    }

    @Override
    public boolean withdrawFromAccount(int accountId, double amount) throws SQLException {
        Accounts account = getAccountById(accountId);
        if (account == null) {
            throw new SQLException("Compte non trouvé.");
        }

        double newBalance = account.getBalance() - amount;
        if (!account.isOverdraftEnabled() && newBalance < 0) {
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
    public Accounts updateAccount(int id, Accounts account) throws SQLException {
        String query = "UPDATE accounts SET account_number = ?, balance = ?, id_users = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getAccount_number());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setInt(3, account.getId_users());
            preparedStatement.setInt(4, id);
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
        account.setAccount_number(resultSet.getString(Accounts.ACCOUNTNUMBER));
        account.setBalance(resultSet.getDouble(Accounts.BALANCE));
        account.setId_users(resultSet.getInt(Accounts.ID_USER));
        return account;
    }
}
