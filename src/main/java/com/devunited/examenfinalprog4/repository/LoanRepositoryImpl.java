package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.Loans;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<Loans> getAllLoans() throws SQLException {
        List<Loans> loans = new ArrayList<>();
        String query = "SELECT * FROM loans";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Loans loan = mapResultSetToLoan(resultSet);
                loans.add(loan);
            }
        }
        return loans;
    }

    @Override
    public Loans getLoanById(int id) throws SQLException {
        String query = "SELECT * FROM loans WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToLoan(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Loans createLoan(Loans loan) throws SQLException {
        String query = "INSERT INTO loans (amount, interest_rate, start_date, end_date, status, creation_date, update_date, id_accounts) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, loan.getAmount());
            preparedStatement.setFloat(2, loan.getInterest_rate());
            preparedStatement.setDate(3, Date.valueOf(loan.getStart_date()));
            preparedStatement.setDate(4, Date.valueOf(loan.getEnd_date()));
            preparedStatement.setString(5, loan.getStatus());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(loan.getCreation_date()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(loan.getUpdate_date()));
            preparedStatement.setInt(8,loan.getId_accounts());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        loan.setId(generatedKeys.getInt(1));
                        return loan;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion du prêt dans la base de données.");
            }
        }
    }

    @Override
    public Loans updateLoan(int id, Loans loan) throws SQLException {
        String query = "UPDATE loans SET amount = ?, interest_rate = ?, start_date = ?, end_date = ?, status = ?, creation_date = ?, update_date = ?, id_accounts = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, loan.getAmount());
            preparedStatement.setFloat(2, loan.getInterest_rate());
            preparedStatement.setDate(3, Date.valueOf(loan.getStart_date()));
            preparedStatement.setDate(4, Date.valueOf(loan.getEnd_date()));
            preparedStatement.setString(5, loan.getStatus());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(loan.getCreation_date()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(loan.getUpdate_date()));
            preparedStatement.setInt(8, loan.getId_accounts());
            preparedStatement.setInt(9, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return loan;
            }
        }
        return null;
    }

    private Loans mapResultSetToLoan(ResultSet resultSet) throws SQLException {
        Loans loan = new Loans();
        loan.setId(resultSet.getInt(Loans.ID));
        loan.setAmount(resultSet.getDouble(Loans.AMOUNT));
        loan.setInterest_rate(resultSet.getFloat(Loans.INTEREST_RATE));
        loan.setStart_date(resultSet.getDate(Loans.START_DATE).toLocalDate());
        loan.setEnd_date(resultSet.getDate(Loans.END_DATE).toLocalDate());
        loan.setStatus(resultSet.getString(Loans.STATUS));
        loan.setCreation_date(resultSet.getTimestamp(Loans.CREATION_DATE).toLocalDateTime());
        loan.setUpdate_date(resultSet.getTimestamp(Loans.UPDATE_DATE).toLocalDateTime());
        loan.setId_accounts(resultSet.getInt(Loans.ID_ACCOUNTS));

        return loan;
    }
}
