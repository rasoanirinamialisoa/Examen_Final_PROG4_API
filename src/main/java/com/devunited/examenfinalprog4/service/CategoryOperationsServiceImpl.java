package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.CategoryOperations;
import com.devunited.examenfinalprog4.repository.CategoryOperationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryOperationsServiceImpl implements CategoryOperationsService {

    private final CategoryOperationsRepository categoryOperationsRepository;

    @Override
    public List<CategoryOperations> getAllCategoryOperations() throws SQLException {
        return categoryOperationsRepository.getAllCategoryOperations();
    }

    @Override
    public CategoryOperations getCategoryOperationsById(int id) throws SQLException {
        return categoryOperationsRepository.getCategoryOperationsById(id);
    }

    @Override
    public CategoryOperations createCategoryOperations(CategoryOperations categoryOperations) throws SQLException {
        return categoryOperationsRepository.createCategoryOperations(categoryOperations);
    }

    @Override
    public CategoryOperations updateCategoryOperations(int id, CategoryOperations categoryOperations) throws SQLException {
        return categoryOperationsRepository.updateCategoryOperations(id, categoryOperations);
    }

    public List<CategoryOperations> getCategoriesByType(String type) throws SQLException {
        return categoryOperationsRepository.findByType(type);
    }

}
