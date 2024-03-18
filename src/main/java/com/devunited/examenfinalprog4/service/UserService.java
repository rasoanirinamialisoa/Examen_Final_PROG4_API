package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<Users> getAllUsers() throws SQLException;
    Users getUserById(int id) throws SQLException;
    Users createUser(Users user) throws SQLException;
    Users updateUser(int id, Users user) throws SQLException;
}
