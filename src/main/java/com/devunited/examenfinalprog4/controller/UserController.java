package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable int id) throws SQLException {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public Users addUser(@RequestBody Users user) throws SQLException {
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable int id, @RequestBody Users user) throws SQLException {
        return userService.updateUser(id, user);
    }
}
