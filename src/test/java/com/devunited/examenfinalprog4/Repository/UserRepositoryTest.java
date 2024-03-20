package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    public void testCreateUser() throws SQLException {
        Users users = new Users();
        userRepositoryImpl.createUser(users);
    }
}
