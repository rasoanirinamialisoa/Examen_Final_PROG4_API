package com.devunited.examenfinalprog4.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loans {
    private int id;
    private double amount;
    private float interest_rate;
    private LocalDate start_date;
    private LocalDate end_date;
    private String status;
    private LocalDateTime creation_date;
    private LocalDateTime update_date;
    private int id_accounts;

    public static final String ID = "id";
    public static final String AMOUNT = "amount";
    public static final String INTEREST_RATE = "interest_rate";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String STATUS = "status";
    public static final String CREATION_DATE = "creation_date";
    public static final String UPDATE_DATE = "update_date";
    public static final String ID_ACCOUNTS = "id_accounts";

}

