package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.AccountType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountTypeRepositoryImpl implements AccountTypeRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<AccountType> getAllAccountTypes() throws SQLException {
        List<AccountType> accountTypes = new ArrayList<>();
        String query = "SELECT * FROM account_types";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                AccountType accountType = mapResultSetToAccountType(resultSet);
                accountTypes.add(accountType);
            }
        }
        return accountTypes;
    }

    @Override
    public AccountType getAccountTypeById(int id) throws SQLException {
        String query = "SELECT * FROM account_types WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAccountType(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public AccountType createAccountType(AccountType accountType) throws SQLException {
        String query = "INSERT INTO account_types (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, accountType.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        accountType.setId(generatedKeys.getInt(1));
                        return accountType;
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            } else {
                throw new SQLException("Failed to insert account type into database.");
            }
        }
    }

    @Override
    public AccountType updateAccountType(int id, AccountType accountType) throws SQLException {
        String query = "UPDATE account_types SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountType.getName());
            preparedStatement.setInt(2, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return accountType;
            }
        }
        return null;
    }

    @Override
    public int getAccountTypeIdByName(String typeName) throws SQLException {
        String query = "SELECT id FROM account_types WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, typeName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        throw new SQLException("No account type found with name: " + typeName);
    }

    @Override
    public List<String> getAllAccountTypeNames() throws SQLException {
        List<String> accountTypeNames = new ArrayList<>();
        String query = "SELECT name FROM account_types";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String accountTypeName = resultSet.getString("name");
                accountTypeNames.add(accountTypeName);
            }
        }
        return accountTypeNames;
    }




    private AccountType mapResultSetToAccountType(ResultSet resultSet) throws SQLException {
        AccountType accountType = new AccountType();
        accountType.setId(resultSet.getInt("id"));
        accountType.setName(resultSet.getString("name"));
        return accountType;
    }
}
