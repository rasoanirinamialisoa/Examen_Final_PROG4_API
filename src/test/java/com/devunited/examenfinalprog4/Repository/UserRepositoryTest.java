package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.Utils;
import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Mock
    private Connection mockConnection;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException("Connection failed"));
        Utils.getMockConnection(mockConnection);
}

    @Test
    public void testGetAllUsers_ShouldThrowSQLException_OnConnectionError() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        assertThrows(SQLException.class, userRepository::getAllUsers);
    }

    @Test
    public void testGetAllUsers_ShouldReturnListOfUsers_OnSuccessfulConnection() {
        try {
            List<Users> users = userRepositoryImpl.getAllUsers();
            assertNotNull(users);
            assertFalse(users.isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserById_ShouldReturnUser(){
        int id = 19;

        try {
            Users user = userRepositoryImpl.getUserById(id);
            assertNotNull(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
