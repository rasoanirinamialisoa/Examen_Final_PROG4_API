package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepository;
import com.devunited.examenfinalprog4.repository.UserRepositoryImpl;
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

public class UserRepositoryImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        Users user2 = new Users(2, "Jane Smith", "janesmith", LocalDate.of(1992, 2, 2), "janesmith@example.com", "password123");
        Users user3 = new Users(3, "Mike Brown", "mikebrown", LocalDate.of(1988, 3, 3), "mikebrown@example.com", "password123");
        Users user4 = new Users(4, "Sarah Davis", "sarahdavis", LocalDate.of(1995, 4, 4), "sarahdavis@example.com", "password123");
        Users user5 = new Users(5, "James Wilson", "jameswilson", LocalDate.of(1991, 5, 5), "jameswilson@example.com", "password123");
        Users user6 = new Users(6, "Linda Johnson", "lindajohnson", LocalDate.of(1989, 6, 6), "lindajohnson@example.com", "password123");
        Users user7 = new Users(7, "Robert Miller", "robertmiller", LocalDate.of(1993, 7, 7), "robertmiller@example.com", "password123");
        Users user8 = new Users(8, "Patricia Garcia", "patriciagarcia", LocalDate.of(1994, 8, 8), "patriciagarcia@example.com", "password123");
        Users user9 = new Users(9, "David Anderson", "davidanderson", LocalDate.of(1990, 9, 9), "davidanderson@example.com", "password123");
        Users user10 = new Users(10, "Jennifer Martinez", "jennifermartinez", LocalDate.of(1987, 10, 10), "jennifermartinez@example.com", "password123");

        when(userRepository.getAllUsers()).thenReturn(Arrays.asList( user2, user3, user4, user5, user6, user7, user8, user9, user10));
        when(userRepository.getUserById(2)).thenReturn(user2);
    }

    @Test
    public void TestFindAllUsers_returnCorrectNumber() throws SQLException {
        List<Users> users = userRepositoryImpl.getAllUsers();

        assertThat(users).hasSize(9);
    }

    @Test
    public void TestFindUserById_returnUser() throws SQLException {
        try {
        Users user = userRepositoryImpl.getUserById(2);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Jane Smith");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Test
    public void TestCreateUser_andUserIsCreatedSuccessfully() throws SQLException {
        try {
        Users newUser = new Users(1, "Paul Walker", "walker",
                LocalDate.of(1980, 10, 30),
                "paul@example.com", "fast4321");
        Users createdUser = userRepositoryImpl.createUser(newUser);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isPositive();
        assertThat(createdUser.getName()).isEqualTo("Paul Walker");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void TestUpdateUser_andUpdatedSuccessfully() throws SQLException {
        try {

        int userIdToUpdate = 9;
        Users updatedUser = new Users(userIdToUpdate, "Dylan Thomas Sprouse", "Thomas Sprouse",
                LocalDate.of(2000, 1, 1),
                "thomas@example.com", "sprouse7410");
        Users result = userRepositoryImpl.updateUser(userIdToUpdate, updatedUser);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Dylan Thomas Sprouse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestFindUserByIdNonExisting_returnNull() throws SQLException {
        try {
        Users nonExistingUser = userRepositoryImpl.getUserById(100);

        assertThat(nonExistingUser).isNull();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

}
