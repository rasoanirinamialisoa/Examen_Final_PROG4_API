package com.devunited.examenfinalprog4.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomeExpenseSummary {
    private String registrationDate;
    private double totalIncome;
    private double totalExpense;

}
