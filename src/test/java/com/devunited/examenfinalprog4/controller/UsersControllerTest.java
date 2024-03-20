package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenUsers_whenGetAllUsers_thenReturnJsonArray() throws Exception {
        // Given
        Users user1 = new Users(1, "John Doe", "johndoe", LocalDate.of(1990, 1, 1), "johndoe@example.com", "password123");
        Users user2 = new Users(2, "Jane Smith", "janesmith", LocalDate.of(1992, 2, 2), "janesmith@example.com", "password123");
        Users user3 = new Users(3, "Mike Brown", "mikebrown", LocalDate.of(1988, 3, 3), "mikebrown@example.com", "password123");
        Users user4 = new Users(4, "Sarah Davis", "sarahdavis", LocalDate.of(1995, 4, 4), "sarahdavis@example.com", "password123");
        Users user5 = new Users(5, "James Wilson", "jameswilson", LocalDate.of(1991, 5, 5), "jameswilson@example.com", "password123");
        Users user6 = new Users(6, "Linda Johnson", "lindajohnson", LocalDate.of(1989, 6, 6), "lindajohnson@example.com", "password123");
        Users user7 = new Users(7, "Robert Miller", "robertmiller", LocalDate.of(1993, 7, 7), "robertmiller@example.com", "password123");
        Users user8 = new Users(8, "Patricia Garcia", "patriciagarcia", LocalDate.of(1994, 8, 8), "patriciagarcia@example.com", "password123");
        Users user9 = new Users(9, "David Anderson", "davidanderson", LocalDate.of(1990, 9, 9), "davidanderson@example.com", "password123");
        Users user10 = new Users(10, "Jennifer Martinez", "jennifermartinez", LocalDate.of(1987, 10, 10), "jennifermartinez@example.com", "password123");

        List<Users> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

        // Mock service response
        given(userService.getAllUsers()).willReturn(users);

        // When & Then
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(users.size())))
                .andExpect(jsonPath("$[0].name").value(user1.getName()))
                .andExpect(jsonPath("$[1].name").value(user2.getName()))
                .andExpect(jsonPath("$[2].name").value(user3.getName()))
                .andExpect(jsonPath("$[3].name").value(user4.getName()))
                .andExpect(jsonPath("$[4].name").value(user5.getName()))
                .andExpect(jsonPath("$[5].name").value(user6.getName()))
                .andExpect(jsonPath("$[6].name").value(user7.getName()))
                .andExpect(jsonPath("$[7].name").value(user8.getName()))
                .andExpect(jsonPath("$[8].name").value(user9.getName()))
                .andExpect(jsonPath("$[9].name").value(user10.getName()));
    }
}
