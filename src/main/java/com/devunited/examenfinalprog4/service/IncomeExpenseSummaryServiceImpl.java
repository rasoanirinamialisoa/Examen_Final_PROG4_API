package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.IncomeExpenseSummary;
import com.devunited.examenfinalprog4.repository.IncomeExpenseSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomeExpenseSummaryServiceImpl implements IncomeExpenseSummaryService {

    private final IncomeExpenseSummaryRepository incomeExpenseSummaryRepository;

    @Autowired
    public IncomeExpenseSummaryServiceImpl(IncomeExpenseSummaryRepository incomeExpenseSummaryRepository) {
        this.incomeExpenseSummaryRepository = incomeExpenseSummaryRepository;
    }

    @Override
    public List<IncomeExpenseSummary> getIncomeExpenseSummary(String startDate, String endDate) {
        return incomeExpenseSummaryRepository.getIncomeExpenseSummary(startDate, endDate);
    }
}

