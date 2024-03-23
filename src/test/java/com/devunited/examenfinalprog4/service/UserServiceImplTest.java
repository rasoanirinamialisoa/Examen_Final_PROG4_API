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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        Users user1 = new Users(1, "John Doe", "johndoe", LocalDate.of(1990, 1, 1), "john@example.com", "password123");
        Users user2 = new Users(2, "Jane Smith", "janesmith", LocalDate.of(1991, 2, 2), "jane@example.com", "password456");
        List<Users> users = Arrays.asList(user1, user2);

        when(userRepository.getAllUsers()).thenReturn(users);

        List<Users> retrievedUsers = userService.getAllUsers();

        assertThat(retrievedUsers).hasSize(2);
        assertThat(retrievedUsers.get(0)).isEqualTo(user1);
        assertThat(retrievedUsers.get(1)).isEqualTo(user2);
    }

    @Test
    public void testGetUserById() throws SQLException {
        int userId = 1;
        Users user = new Users(userId, "John Doe", "johndoe", LocalDate.of(1990, 1, 1), "john@example.com", "password123");

        when(userRepository.getUserById(userId)).thenReturn(user);

        Users retrievedUser = userService.getUserById(userId);

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getId()).isEqualTo(userId);
        assertThat(retrievedUser.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testCreateUser() throws SQLException {
        Users newUser = new Users(1, "Jane Smith", "janesmith", LocalDate.of(1991, 2, 2), "jane@example.com", "password456");
        Users createdUser = new Users(1, "Jane Smith", "janesmith", LocalDate.of(1991, 2, 2), "jane@example.com", "password456");

        when(userRepository.createUser(newUser)).thenReturn(createdUser);

        Users result = userService.createUser(newUser);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Jane Smith");
    }

    @Test
    public void testUpdateUser() throws SQLException {
        int userIdToUpdate = 1;
        Users updatedUser = new Users(userIdToUpdate, "Dylan Thomas Sprouse", "Thomas Sprouse",
                LocalDate.of(1990, 1, 1), "thomas@example.com", "sprouse7410");

        when(userRepository.updateUser(userIdToUpdate, updatedUser)).thenReturn(updatedUser);

        Users result = userService.updateUser(userIdToUpdate, updatedUser);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userIdToUpdate);
        assertThat(result.getName()).isEqualTo("Dylan Thomas Sprouse");
    }
}
