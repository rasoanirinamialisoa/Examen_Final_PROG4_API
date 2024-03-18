package com.devunited.examenfinalprog4.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transfers {
    private int id;
    private double amount;
    private boolean same_bank;
    private boolean other_bank;
    private String other_bank_name;
    private String other_account_number;

    public static final String ID = "id";
    public static final String AMOUNT = "amount";
    public static final String SAME_BANK = "same_bank";
    public static final String OTHER_BANK = "other_bank";
    public static final String OTHER_BANK_NAME = "other_bank_name";
    public static final String OTHER_ACCOUNT_NUMBER = "other_account_number";

}

