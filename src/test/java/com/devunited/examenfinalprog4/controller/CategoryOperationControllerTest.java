package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.CategoryOperations;
import com.devunited.examenfinalprog4.service.CategoryOperationsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryOperationsController.class)
public class CategoryOperationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryOperationsService categoryOperationsService;


    @Test
    public void testGetAllCategoryOperations_Success() throws Exception {

        List<CategoryOperations> categoryOperations = new ArrayList<>();
        categoryOperations.add(new CategoryOperations(1, "Category 1"));
        categoryOperations.add(new CategoryOperations(2, "Category 2"));

        when(categoryOperationsService.getAllCategoryOperations()).thenReturn(categoryOperations);

        mockMvc.perform(get("/api/categoryOperations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Category 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Category 2"));
    }

    @Test
    public void testGetCategoryOperationsById_Success() throws Exception {

        int categoryId = 11;
        CategoryOperations categoryOperation = new CategoryOperations(11, "Other");

        when(categoryOperationsService.getCategoryOperationsById(categoryId)).thenReturn(categoryOperation);

        mockMvc.perform(get("/api/categoryOperations/{id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.name").value("Other"));
    }

    @Test
    public void testAddCategoryOperations_Success() throws Exception {

        CategoryOperations categoryOperation = new CategoryOperations(11, "Other");

        when(categoryOperationsService.createCategoryOperations(any(CategoryOperations.class))).thenReturn(categoryOperation);

        mockMvc.perform(post("/api/categoryOperations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":11,\"name\":\"Other\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.name").value("Other"));
    }

    @Test
    public void testUpdateCategoryOperations_Success() throws Exception {

        int categoryId = 4;
        CategoryOperations categoryOperation = new CategoryOperations(4, "Sport");

        when(categoryOperationsService.updateCategoryOperations(eq(categoryId), any(CategoryOperations.class))).thenReturn(categoryOperation);

        mockMvc.perform(put("/api/categoryOperations/{id}", categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":4,\"name\":\"Sport\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Sport"));
    }
}
