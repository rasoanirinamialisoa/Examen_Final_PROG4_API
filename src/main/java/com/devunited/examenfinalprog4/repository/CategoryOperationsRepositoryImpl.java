package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.config.ConnectDatabase;
import com.devunited.examenfinalprog4.model.CategoryOperations;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

    @Override
    public List<CategoryOperations> findByType(String type) throws SQLException {
        List<CategoryOperations> categories = new ArrayList<>();
        String sql = "SELECT * FROM category_operations WHERE type = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CategoryOperations category = new CategoryOperations();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    category.setType(resultSet.getString("type"));

                    categories.add(category);
                }
            }
        }

        return categories;
    }

    @Override
    public List<CategoryOperations> getCategorySummary(String startDate, String endDate) throws SQLException {
        List<CategoryOperations> categorySummaries = new ArrayList<>();
        String query = "SELECT * FROM get_category_summary(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CategoryOperations categoryOperation = mapResultSetToCategoryOperations(resultSet);
                    categorySummaries.add(categoryOperation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorySummaries;
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
