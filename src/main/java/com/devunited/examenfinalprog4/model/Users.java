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
    private String name;
    private String username;
    private LocalDate birthday;
    private String email;
    private String password;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

}
