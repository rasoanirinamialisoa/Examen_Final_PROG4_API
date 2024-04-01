package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.Transactions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        List<Transactions> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Transactions transaction = mapResultSetToTransaction(resultSet);
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public Transactions getTransactionById(int id) throws SQLException {
        String query = "SELECT * FROM transactions WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTransaction(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Transactions createTransaction(Transactions transaction) throws SQLException {
        String query = "INSERT INTO transactions (type, amount, id_accounts, id_category_operation, effective_date, registration_date ) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setInt(3, transaction.getId_accounts());
            preparedStatement.setInt(4, transaction.getId_category_operation());
            preparedStatement.setDate(5, Date.valueOf(transaction.getEffective_date().toLocalDate()));
            preparedStatement.setDate(6,Date.valueOf(transaction.getRegistration_date().toLocalDate()));


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        transaction.setId(generatedKeys.getInt(1));
                        return transaction;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion de la transaction dans la base de données.");
            }
        }
    }

    @Override
    public Transactions updateTransaction(int id, Transactions transaction) throws SQLException {
        String query = "UPDATE transactions SET type = ?, amount = ?, id_accounts = ?, id_category_operation = ?, effective_date = ?, registration_date = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setInt(3, transaction.getId_accounts());
            preparedStatement.setInt(4, transaction.getId_category_operation());
            preparedStatement.setDate(5, Date.valueOf(transaction.getEffective_date().toLocalDate()));
            preparedStatement.setDate(6,Date.valueOf(transaction.getRegistration_date().toLocalDate()));
            preparedStatement.setInt(8, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return transaction;
            }
        }
        return null;
    }
    @Override
    public void updateAccountBalance(int id, BigDecimal amount) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

    private Transactions mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        Transactions transaction = new Transactions();
        transaction.setId(resultSet.getInt(Transactions.ID));
        transaction.setType(resultSet.getString(Transactions.TYPE));
        transaction.setAmount(resultSet.getDouble(Transactions.AMOUNT));
        transaction.setId_accounts(resultSet.getInt(Transactions.ID_ACCOUNTS));
        transaction.setId_category_operation(resultSet.getInt(Transactions.ID_CATEGORY_OPERATION));
        transaction.setEffective_date(LocalDate.parse(resultSet.getString(Transactions.EFFECTIVE_DATE)).atStartOfDay());
        transaction.setRegistration_date(LocalDate.parse(resultSet.getString(Transactions.REGISTRATION_DATE)).atStartOfDay());
        return transaction;
    }



}
