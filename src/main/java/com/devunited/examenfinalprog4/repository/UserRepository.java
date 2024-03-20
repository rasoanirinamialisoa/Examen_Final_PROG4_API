package com.devunited.examenfinalprog4.repository;

import com.devunited.examenfinalprog4.model.Users;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository

public interface UserRepository {
    List<Users> getAllUsers() throws SQLException;
    Users getUserById(int id) throws SQLException;
    Users createUser(Users user) throws SQLException;
    Users updateUser(int id, Users user) throws SQLException;
}

