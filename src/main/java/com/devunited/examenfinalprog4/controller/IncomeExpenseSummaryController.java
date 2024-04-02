package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.IncomeExpenseSummary;
import com.devunited.examenfinalprog4.service.IncomeExpenseSummaryService;
import com.devunited.examenfinalprog4.controller.DateRangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IncomeExpenseSummaryController {

    private final IncomeExpenseSummaryService incomeExpenseSummaryService;

    @Autowired
    public IncomeExpenseSummaryController(IncomeExpenseSummaryService incomeExpenseSummaryService) {
        this.incomeExpenseSummaryService = incomeExpenseSummaryService;
    }

    @GetMapping("/income-expense-summary")
    public ResponseEntity<List<IncomeExpenseSummary>> getIncomeExpenseSummary(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<IncomeExpenseSummary> incomeExpenseSummaries = incomeExpenseSummaryService.getIncomeExpenseSummary(startDate, endDate);
            return ResponseEntity.ok(incomeExpenseSummaries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
