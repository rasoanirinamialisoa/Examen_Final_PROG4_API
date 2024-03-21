package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsersServiceTest {

    @Mock
    private UserRepository usersRepository;

    @InjectMocks
    private UserServiceImpl usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() throws SQLException {
        // Given
        List<Users> usersList = Arrays.asList(
                new Users(1, "John Doe", "johndoe", LocalDate.of(1990, 5, 15), "john.doe@example.com", "password123"),
                new Users(2, "Jane Doe", "janedoe", LocalDate.of(1992, 8, 20), "jane.doe@example.com", "password456")
        );
        when(usersRepository.getAllUsers()).thenReturn(usersList);

        // When
        List<Users> result = usersService.getAllUsers();

        // Then
        assertEquals(usersList.size(), result.size());
        assertEquals(usersList.get(0), result.get(0));
        assertEquals(usersList.get(1), result.get(1));
    }

    // Test other methods similarly
}
