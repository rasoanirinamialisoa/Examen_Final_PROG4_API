package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserRepositoryTest {
    private UserRepositoryImpl userRepository;
    private ResultSet resultSet;

    @BeforeEach
    public void setup() throws SQLException {
        userRepository = new UserRepositoryImpl();

        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        try {
        List<Users> expectedUsers = new ArrayList<>();
        Users user1 = new Users();
        user1.setId(1);
        user1.setName("John");
        user1.setUsername("john_doe");
        user1.setBirthday(LocalDate.of(1995, 5, 25));
        user1.setEmail("john@gmail.com");
        user1.setPassword("johnpassword");
        expectedUsers.add(user1);

        Users user2 = new Users();
        user2.setId(2);
        user2.setName("Alice");
        user2.setUsername("alice_smith");
        user2.setBirthday(LocalDate.of(2000, 10, 20));
        user2.setEmail("alice@gmail.com");
        user2.setPassword("alicepassword");
        expectedUsers.add(user2);

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("name")).thenReturn("John", "Alice");
        when(resultSet.getString("username")).thenReturn("john_doe", "alice_smith");
        when(resultSet.getDate("birthday")).thenReturn(Date.valueOf(LocalDate.of(1995, 5, 25)),
                Date.valueOf(LocalDate.of(2000, 10, 20)));
        when(resultSet.getString("email")).thenReturn("john@gmail.com", "alice@gmail.com");
        when(resultSet.getString("password")).thenReturn("johnpassword", "alicepassword");

        List<Users> actualUsers = userRepository.getAllUsers();

        Assertions.assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            Assertions.assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception...
        }
    }
}
