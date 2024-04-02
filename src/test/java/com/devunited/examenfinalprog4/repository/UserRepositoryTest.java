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

public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        Users user2 = new Users(2, "Jane", "Smith", LocalDate.of(1992, 2, 2), 10000.00, "janesmith@example.com", "password123", 1);
        Users user3 = new Users(3, "Mike ", "Brown", LocalDate.of(1988, 3, 3), 4500.00, "mikebrown@example.com", "password123", 2);
        Users user4 = new Users(4, "Sarah Davis", "sarahdavis", LocalDate.of(1995, 4, 4), 1500.00, "sarahdavis@example.com", "password123", 3);
        Users user5 = new Users(5, "James Wilson", "jameswilson", LocalDate.of(1991, 5, 5), 5200.00, "jameswilson@example.com", "password123", 4);
        Users user6 = new Users(6, "Linda Johnson", "lindajohnson", LocalDate.of(1989, 6, 6), 800.00, "lindajohnson@example.com", "password123", 5);
        Users user7 = new Users(7, "Robert Miller", "robertmiller", LocalDate.of(1993, 7, 7), 4100.12, "robertmiller@example.com", "password123", 6);
        Users user8 = new Users(8, "Patricia Garcia", "patriciagarcia", LocalDate.of(1994, 8, 8), 7200.00, "patriciagarcia@example.com", "password123", 7);
        Users user9 = new Users(9, "David Anderson", "davidanderson", LocalDate.of(1990, 9, 9), 8500.00,"davidanderson@example.com", "password123", 8);
        Users user10 = new Users(10, "Jennifer Martinez", "jennifermartinez", LocalDate.of(1987, 10, 10), 582.00, "jennifermartinez@example.com", "password123", 9);

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
        assertThat(user.getFirst_name()).isEqualTo("Jane");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Test
    public void TestCreateUser_andUserIsCreatedSuccessfully(){
        try {
        Users newUser = new Users(1, "Paul ", "walker",
                LocalDate.of(1980, 10, 30), 90000.00,
                "paul@example.com", "fast4321", 11);
        Users createdUser = userRepositoryImpl.createUser(newUser);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isPositive();
        assertThat(createdUser.getFirst_name()).isEqualTo("Paul");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void TestUpdateUser_andUpdatedSuccessfully() throws SQLException {
        try {

        int userIdToUpdate = 9;
        Users updatedUser = new Users(userIdToUpdate, "Dylan Thomas Sprouse", "Thomas Sprouse",
                LocalDate.of(1990, 1, 1), 14500.00,
                "thomas@example.com", "sprouse7410", 9);
        Users result = userRepositoryImpl.updateUser(userIdToUpdate, updatedUser);

        assertThat(result).isNotNull();
        assertThat(result.getFirst_name()).isEqualTo("Dylan");
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
