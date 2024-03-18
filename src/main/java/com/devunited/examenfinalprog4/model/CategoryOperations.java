package com.devunited.examenfinalprog4.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryOperations {
    private int id;
    private String name;

    public static final String ID = "id";
    public static final String NAME = "name";
}
