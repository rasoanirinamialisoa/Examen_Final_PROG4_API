package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.IncomeExpenseSummary;
import java.util.List;

public interface IncomeExpenseSummaryRepository {
    List<IncomeExpenseSummary> getIncomeExpenseSummary(String startDate, String endDate);
}
