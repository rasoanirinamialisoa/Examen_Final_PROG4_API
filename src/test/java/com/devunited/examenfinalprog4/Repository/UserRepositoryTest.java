package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.Utils;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {
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
}
