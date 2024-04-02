package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.IncomeExpenseSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeExpenseSummaryRepositoryImpl implements IncomeExpenseSummaryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<IncomeExpenseSummary> getIncomeExpenseSummary(String startDate, String endDate) {
        String query = "SELECT * FROM get_income_expense_summary(?, ?)";
        return jdbcTemplate.query(query, new Object[]{startDate, endDate}, (rs, rowNum) ->
                new IncomeExpenseSummary(
                        rs.getString("registration_date"),
                        rs.getDouble("total_income"),
                        rs.getDouble("total_expense")
                ));
    }
}
