package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.CategoryOperations;
import com.devunited.examenfinalprog4.repository.CategoryOperationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryOperationServiceTest {
    private CategoryOperationsServiceImpl categoryOperationsService;
    private CategoryOperationsRepository categoryOperationsRepository;

    @BeforeEach
    public void setUp() {
        categoryOperationsRepository = mock(CategoryOperationsRepository.class);
        categoryOperationsService = new CategoryOperationsServiceImpl(categoryOperationsRepository);
    }

    @Test
    public void testGetAllCategoryOperations_Success() throws SQLException {

        List<CategoryOperations> expectedCategoryOperations = new ArrayList<>();
        expectedCategoryOperations.add(new CategoryOperations(4, "Salary"));
        expectedCategoryOperations.add(new CategoryOperations(7, "education"));

        when(categoryOperationsRepository.getAllCategoryOperations()).thenReturn(expectedCategoryOperations);

        List<CategoryOperations> actualCategoryOperations = categoryOperationsService.getAllCategoryOperations();

        assertEquals(expectedCategoryOperations, actualCategoryOperations);
    }

    @Test
    public void testGetCategoryOperationsById_Success() throws SQLException {

        int categoryId = 1;
        CategoryOperations expectedCategoryOperation = new CategoryOperations(4, "Salary");

        when(categoryOperationsRepository.getCategoryOperationsById(categoryId)).thenReturn(expectedCategoryOperation);

        CategoryOperations actualCategoryOperation = categoryOperationsService.getCategoryOperationsById(categoryId);

        assertEquals(expectedCategoryOperation, actualCategoryOperation);
    }

    @Test
    public void testCreateCategoryOperations_Success() throws SQLException {

        CategoryOperations categoryOperation = new CategoryOperations(11, "Sport");

        when(categoryOperationsRepository.createCategoryOperations(categoryOperation)).thenReturn(categoryOperation);

        CategoryOperations createdCategoryOperation = categoryOperationsService.createCategoryOperations(categoryOperation);

        assertEquals(categoryOperation, createdCategoryOperation);
    }

    @Test
    public void testUpdateCategoryOperations_Success() throws SQLException {

        int categoryId = 4;
        CategoryOperations categoryOperation = new CategoryOperations(4, "Sport");

        when(categoryOperationsRepository.updateCategoryOperations(categoryId, categoryOperation)).thenReturn(categoryOperation);

        CategoryOperations updatedCategoryOperation = categoryOperationsService.updateCategoryOperations(categoryId, categoryOperation);

        assertEquals(categoryOperation, updatedCategoryOperation);
    }
}
