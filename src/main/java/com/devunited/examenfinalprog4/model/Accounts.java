package com.devunited.examenfinalprog4.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    private int id;
    private String account_number;
    private double balance;
    private int user_id;
    private int type_id;
    private boolean allows_overdraft;
    private double overdraft_credit_percentage;
    private double interest_rate_7_days;
    private double interest_rate_after_7_days;

    public static final String ID = "id";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String BALANCE = "balance";
    public static final String USER_ID = "user_id";
    public static final String TYPE_ID = "type_id";
    public static final String ALLOWS_OVERDRAFT = "allows_overdraft";
    public static final String OVERDRAFT_CREDIT_PERCENTAGE = "overdraft_credit_percentage";
    public static final String INTEREST_RATE_7_DAYS = "interest_rate_7_days";
    public static final String INTEREST_RATE_AFTER_7_DAYS = "interest_rate_after_7_days";

}
