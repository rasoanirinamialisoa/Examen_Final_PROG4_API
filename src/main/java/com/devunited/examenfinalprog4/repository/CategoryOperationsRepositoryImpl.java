package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.CategoryOperations;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryOperationsRepositoryImpl implements CategoryOperationsRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    @Override
    public List<CategoryOperations> getAllCategoryOperations() throws SQLException {
        List<CategoryOperations> categoryOperations = new ArrayList<>();
        String query = "SELECT * FROM category_operation";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CategoryOperations categoryOperation = mapResultSetToCategoryOperations(resultSet);
                categoryOperations.add(categoryOperation);
            }
        }
        return categoryOperations;
    }

    @Override
    public CategoryOperations getCategoryOperationsById(int id) throws SQLException {
        String query = "SELECT * FROM category_operation WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCategoryOperations(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public CategoryOperations createCategoryOperations(CategoryOperations categoryOperations) throws SQLException {
        String query = "INSERT INTO category_operation (name, description, type) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, categoryOperations.getName());
            preparedStatement.setString(2,categoryOperations.getDescription());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categoryOperations.setId(generatedKeys.getInt(1));
                        return categoryOperations;
                    } else {
                        throw new SQLException("Échec de récupération de l'ID généré.");
                    }
                }
            } else {
                throw new SQLException("Échec de l'insertion de la catégorie d'opérations dans la base de données.");
            }
        }
    }

    @Override
    public CategoryOperations updateCategoryOperations(int id, CategoryOperations categoryOperations) throws SQLException {
        String query = "UPDATE category_operation SET name = ?, description = ?, type = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, categoryOperations.getName());
            preparedStatement.setString(2,categoryOperations.getDescription());
            preparedStatement.setInt(3, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return categoryOperations;
            }
        }
        return null;
    }

    private CategoryOperations mapResultSetToCategoryOperations(ResultSet resultSet) throws SQLException {
        CategoryOperations categoryOperation = new CategoryOperations();
        categoryOperation.setId(resultSet.getInt(CategoryOperations.ID));
        categoryOperation.setName(resultSet.getString(CategoryOperations.NAME));
        categoryOperation.setDescription(resultSet.getString(CategoryOperations.DESCRIPTION));
        categoryOperation.setType(resultSet.getString(CategoryOperations.TYPE));
        return categoryOperation;
    }
}
