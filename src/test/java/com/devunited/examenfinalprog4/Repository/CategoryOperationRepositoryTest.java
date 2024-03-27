package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.CategoryOperations;
import com.devunited.examenfinalprog4.repository.CategoryOperationsRepository;
import com.devunited.examenfinalprog4.repository.CategoryOperationsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryOperationRepositoryTest {
    @Mock
    private CategoryOperationsRepository categoryOperationsRepository;

    @InjectMocks
    private CategoryOperationsRepositoryImpl categoryOperationsRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        CategoryOperations category1 = new CategoryOperations(1, "Groceries");
        CategoryOperations category2 = new CategoryOperations(2, "Utilities");
        CategoryOperations category3 = new CategoryOperations(3, "Rent");
        CategoryOperations category4 = new CategoryOperations(4,"Salary");
        CategoryOperations category5 = new CategoryOperations(5,"Entertainment");
        CategoryOperations category6 = new CategoryOperations(6, "Travel");
        CategoryOperations category7 = new CategoryOperations(7, "Education");
        CategoryOperations category8 = new CategoryOperations(8, "Healthcare");
        CategoryOperations category9 = new CategoryOperations(9, "Insurance");
        CategoryOperations category10 = new CategoryOperations(10, "Investments");

        when(categoryOperationsRepository.getAllCategoryOperations()).thenReturn(Arrays
                .asList( category1, category2, category3, category4, category5,
                        category6, category7, category8, category9, category10));
        when(categoryOperationsRepository.getCategoryOperationsById(2)).thenReturn(category2);
    }

    @Test
    public void findAll_andReturnCorrectNumberOfCategory() throws SQLException {
        List<CategoryOperations> categoryOperations = categoryOperationsRepositoryImpl.getAllCategoryOperations();

        assertThat(categoryOperations).hasSize(17);
    }

    @Test
    public void findById_andReturnCategory() throws SQLException {
        CategoryOperations categoryOperations = categoryOperationsRepositoryImpl.getCategoryOperationsById(4);

        assertThat(categoryOperations).isNotNull();
        assertThat(categoryOperations.getName()).isEqualTo("sport");
    }

    @Test
    public void createCategory_andCreatedSuccessfully() throws SQLException {
        CategoryOperations newCategory = new CategoryOperations(10, "Sport");
        CategoryOperations createdCategory = categoryOperationsRepositoryImpl.createCategoryOperations(newCategory);

        assertThat(createdCategory).isNotNull();
        assertThat(createdCategory.getId()).isPositive();
        assertThat(createdCategory.getName()).isEqualTo("Sport");
    }


    @Test
    public void updateCategory_andUpdatedSuccessfully() throws SQLException {
        int categoryIdToUpdate = 4;
        CategoryOperations updatedCategory= new CategoryOperations(categoryIdToUpdate, "sport");
        CategoryOperations result = categoryOperationsRepositoryImpl
                .updateCategoryOperations(categoryIdToUpdate, updatedCategory);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("sport");
    }

    @Test
    public void findByIdNonExisting_thenReturnNull() throws SQLException {
        CategoryOperations nonExistingUser = categoryOperationsRepositoryImpl.getCategoryOperationsById(50);

        assertThat(nonExistingUser).isNull();
    }
}
