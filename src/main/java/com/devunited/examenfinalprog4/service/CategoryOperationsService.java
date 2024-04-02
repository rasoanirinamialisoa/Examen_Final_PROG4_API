package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.CategoryOperations;

import java.sql.SQLException;
import java.util.List;

public interface CategoryOperationsService {
    List<CategoryOperations> getAllCategoryOperations() throws SQLException;
    CategoryOperations getCategoryOperationsById(int id) throws SQLException;
    CategoryOperations createCategoryOperations(CategoryOperations categoryOperations) throws SQLException;
    CategoryOperations updateCategoryOperations(int id, CategoryOperations categoryOperations) throws SQLException;

    List<CategoryOperations> getCategoriesByType(String type) throws SQLException;

    List<CategoryOperations> getCategorySummary(String startDate, String endDate) throws SQLException;
}
