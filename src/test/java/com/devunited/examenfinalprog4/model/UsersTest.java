package com.devunited.examenfinalprog4.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    @Test
    public void testGetterAndSetter() {
        Users user = new Users();
        user.setId(1);
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setBirthday(LocalDate.of(1990, 5, 15));
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe", user.getUsername());
        assertEquals(LocalDate.of(1990, 5, 15), user.getBirthday());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }
}

