package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.Users;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<Users> getAllUsers() throws SQLException {
        List<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Users user = mapResultSetToUser(resultSet);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public Users getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Users createUser(Users user) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, birth_date, monthly_salary, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLast_name());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setDate(3, Date.valueOf(user.getBirth_date()));
            preparedStatement.setDouble(4, user.getMonthly_salary());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        return user;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion de l'utilisateur dans la base de données.");
            }
        }
    }

    @Override
    public Users updateUser(int id, Users user) throws SQLException {
        String query = "UPDATE users SET first_name = ?, last_name = ?, birth_date = ?, monthly_salary = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setDate(3, Date.valueOf(user.getBirth_date()));
            preparedStatement.setDouble(4, user.getMonthly_salary());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return user;
            }
        }
        return null;
    }

    private Users mapResultSetToUser(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getInt(Users.ID));
        user.setFirst_name(resultSet.getString(Users.NAME));
        user.setLast_name(resultSet.getString(Users.USERNAME));
        user.setBirth_date(resultSet.getDate(Users.BIRTHDAY).toLocalDate());
        user.setMonthly_salary(resultSet.getDouble(Users.MONTHLY_SALARY));
        user.setEmail(resultSet.getString(Users.EMAIL));
        user.setPassword(resultSet.getString(Users.PASSWORD));
        return user;
    }
}
