package com.devunited.examenfinalprog4.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Accounts {
    private int id;
    private String account_number;
    private double balance;
    private int id_users;
    private boolean overdraftEnabled; // Indique si le découvert est activé pour ce compte
    private double creditLimit; // Limite de crédit autorisée pour ce compte
    private double interestRate7Days; // Taux d'intérêt pour les 7 premiers jours de découvert
    private double interestRateAfter7Days; //

    public static final String ID = "id";
    public static final String ACCOUNTNUMBER = "account_number";
    public static final String BALANCE = "balance";
    public static final String ID_USER = "id_users";
    public static final String OVERDRAFT_ENABLED = "overdraft_enabled";
    public static final String CREDIT_LIMIT = "credit_limit";
    public static final String INTEREST_RATE_7_DAYS = "interest_rate_7_days";
    public static final String INTEREST_RATE_AFTER_7_DAYS = "interest_rate_after_7_days";
}
