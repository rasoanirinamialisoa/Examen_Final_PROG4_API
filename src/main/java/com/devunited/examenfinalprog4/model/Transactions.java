package com.devunited.examenfinalprog4.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transactions {

    private int id;
    private String type;
    private LocalDate date;
    private double amount;
    private int id_accounts;
    private int id_category_operation;

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";
    public static final String ID_ACCOUNTS = "id_accounts";
    public static final String ID_CATEGORY_OPERATION = "id_category_operation";
}

