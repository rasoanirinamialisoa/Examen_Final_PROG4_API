package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.CategoryOperations;
import com.devunited.examenfinalprog4.service.CategoryOperationsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryOperationsController {

    private final CategoryOperationsService categoryOperationsService;

    public CategoryOperationsController(CategoryOperationsService categoryOperationsService) {
        this.categoryOperationsService = categoryOperationsService;
    }

    @GetMapping("/categoryOperations")
    public List<CategoryOperations> getAllCategoryOperations() throws SQLException {
        return categoryOperationsService.getAllCategoryOperations();
    }

    @GetMapping("/categoryOperations/{id}")
    public CategoryOperations getCategoryOperationsById(@PathVariable int id) throws SQLException {
        return categoryOperationsService.getCategoryOperationsById(id);
    }

    @PostMapping("/categoryOperations")
    public CategoryOperations addCategoryOperations(@RequestBody CategoryOperations categoryOperations) throws SQLException {
        return categoryOperationsService.createCategoryOperations(categoryOperations);
    }

    @PutMapping("/categoryOperations/{id}")
    public CategoryOperations updateCategoryOperations(@PathVariable int id, @RequestBody CategoryOperations categoryOperations) throws SQLException {
        return categoryOperationsService.updateCategoryOperations(id, categoryOperations);
    }
}
