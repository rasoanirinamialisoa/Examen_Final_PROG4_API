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

    public static final String ID = "id";
    public static final String ACCOUNTNUMBER = "account_number";
    public static final String BALANCE = "balance";
    public static final String ID_USER = "id_users";

}
