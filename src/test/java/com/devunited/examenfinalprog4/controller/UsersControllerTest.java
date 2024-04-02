package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUsers_whenGetAllUsers_thenReturnJsonArray() throws Exception {
        // Given
        Users user2 = new Users(2, "Jane", "Smith", LocalDate.of(1992, 2, 2), 10000.00, "janesmith@example.com", "password123", 1);
        Users user3 = new Users(3, "Mike ", "Brown", LocalDate.of(1988, 3, 3), 4500.00, "mikebrown@example.com", "password123", 2);
        Users user4 = new Users(4, "Sarah Davis", "sarahdavis", LocalDate.of(1995, 4, 4), 1500.00, "sarahdavis@example.com", "password123", 3);
        Users user5 = new Users(5, "James Wilson", "jameswilson", LocalDate.of(1991, 5, 5), 5200.00, "jameswilson@example.com", "password123", 4);
        Users user6 = new Users(6, "Linda Johnson", "lindajohnson", LocalDate.of(1989, 6, 6), 800.00, "lindajohnson@example.com", "password123", 5);
        Users user7 = new Users(7, "Robert Miller", "robertmiller", LocalDate.of(1993, 7, 7), 4100.12, "robertmiller@example.com", "password123", 6);
        Users user8 = new Users(8, "Patricia Garcia", "patriciagarcia", LocalDate.of(1994, 8, 8), 7200.00, "patriciagarcia@example.com", "password123", 7);
        Users user9 = new Users(9, "David Anderson", "davidanderson", LocalDate.of(1990, 9, 9), 8500.00,"davidanderson@example.com", "password123", 8);
        Users user10 = new Users(10, "Jennifer Martinez", "jennifermartinez", LocalDate.of(1987, 10, 10), 582.00, "jennifermartinez@example.com", "password123", 9);

        List<Users> users = new ArrayList<>();

        // Mock service response
        given(userService.getAllUsers()).willReturn(users);

        // When & Then
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(users.size())))
                .andExpect(jsonPath("$[0].name").value(user2.getFirst_name()))
                .andExpect(jsonPath("$[1].name").value(user3.getFirst_name()))
                .andExpect(jsonPath("$[2].name").value(user4.getFirst_name()))
                .andExpect(jsonPath("$[3].name").value(user5.getFirst_name()))
                .andExpect(jsonPath("$[4].name").value(user6.getFirst_name()))
                .andExpect(jsonPath("$[5].name").value(user7.getFirst_name()))
                .andExpect(jsonPath("$[6].name").value(user8.getFirst_name()))
                .andExpect(jsonPath("$[7].name").value(user9.getFirst_name()))
                .andExpect(jsonPath("$[8].name").value(user10.getFirst_name()));

    }
    @Test
    public void givenUser_whenGetUserById_thenReturnJson() throws Exception {

        int userId = 1;
        Users user = new Users(userId, "John", "Doe",
                LocalDate.of(1990, 1, 1), 5000.00,
                "johndoe@example.com", "password123", 2);

        given(userService.getUserById(userId)).willReturn(user);

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value(user.getFirst_name()));
    }

    @Test
    public void givenUser_whenAddUser_thenReturnJson() throws Exception {

        Users newUser = new Users(1, "Jane", " Smith",
                LocalDate.of(1991, 2, 2), 5800.00,
                "jane@example.com", "password456", 1);

        given(userService.createUser(any(Users.class))).willReturn(newUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(newUser.getId()))
                .andExpect(jsonPath("$.name").value(newUser.getFirst_name()));
    }


}
