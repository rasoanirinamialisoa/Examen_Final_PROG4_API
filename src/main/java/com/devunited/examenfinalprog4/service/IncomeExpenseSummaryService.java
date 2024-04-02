package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.IncomeExpenseSummary;
import java.util.List;

public interface IncomeExpenseSummaryService {
    List<IncomeExpenseSummary> getIncomeExpenseSummary(String startDate, String endDate);
}
