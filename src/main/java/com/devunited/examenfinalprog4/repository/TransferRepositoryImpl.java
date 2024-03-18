package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.Transfers;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransferRepositoryImpl implements TransferRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<Transfers> getAllTransfers() throws SQLException {
        List<Transfers> transfers = new ArrayList<>();
        String query = "SELECT * FROM transfers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Transfers transfer = mapResultSetToTransfer(resultSet);
                transfers.add(transfer);
            }
        }
        return transfers;
    }

    @Override
    public Transfers getTransferById(String id) throws SQLException {
        String query = "SELECT * FROM transfers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTransfer(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Transfers createTransfer(Transfers transfer) throws SQLException {
        String query = "INSERT INTO transfers ( to_account_id, amount, same_bank, other_bank, other_bank_name) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, transfer.getId());
            preparedStatement.setString(2, transfer.getTo_account_id());
            preparedStatement.setDouble(3, transfer.getAmount());
            preparedStatement.setBoolean(4, transfer.isSame_bank());
            preparedStatement.setBoolean(5, transfer.isOther_bank());
            preparedStatement.setString(6, transfer.getOther_bank_name());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        transfer.setId(generatedKeys.getString(1));
                        return transfer;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion du transfert dans la base de données.");
            }
        }
    }

    @Override
    public Transfers updateTransfer(String id, Transfers transfer) throws SQLException {
        String query = "UPDATE transfers SET toAccountId = ?, amount = ?, same_bank = ?, other_bank = ?, other_bank_name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(2, transfer.getTo_account_id());
            preparedStatement.setDouble(3, transfer.getAmount());
            preparedStatement.setBoolean(4, transfer.isSame_bank());
            preparedStatement.setBoolean(5, transfer.isOther_bank());
            preparedStatement.setString(6, transfer.getOther_bank_name());
            preparedStatement.setString(8, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return transfer;
            }
        }
        return null;
    }

    private Transfers mapResultSetToTransfer(ResultSet resultSet) throws SQLException {
        Transfers transfer = new Transfers();
        transfer.setId(resultSet.getString(Transfers.ID));
        transfer.setTo_account_id(resultSet.getString(Transfers.TO_ACCOUNT_ID));
        transfer.setAmount(resultSet.getDouble(Transfers.AMOUNT));
        transfer.setSame_bank(resultSet.getBoolean(Transfers.SAME_BANK));
        transfer.setOther_bank(resultSet.getBoolean(Transfers.OTHER_BANK));
        transfer.setOther_bank_name(resultSet.getString(Transfers.OTHER_BANK_NAME));
        return transfer;
    }
}
