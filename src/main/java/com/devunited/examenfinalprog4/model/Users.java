package com.devunited.examenfinalprog4.model;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate birth_date;
    private double monthly_salary;
    private String email;
    private String password;



    public static final String ID = "id";
    public static final String NAME = "first_name";
    public static final String USERNAME = "last_name";
    public static final String BIRTHDAY = "birth_date";
    public static final String MONTHLY_SALARY = "monthly_salary";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


}
