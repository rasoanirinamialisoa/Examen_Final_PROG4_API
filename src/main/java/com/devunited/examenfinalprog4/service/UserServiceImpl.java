package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Users;
import com.devunited.examenfinalprog4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    @Override
    public Users getUserById(int id) throws SQLException {
        return userRepository.getUserById(id);
    }

    @Override
    public Users createUser(Users user) throws SQLException {
        return userRepository.createUser(user);
    }

    @Override
    public Users updateUser(int id, Users user) throws SQLException {
        return userRepository.updateUser(id, user);
    }
}
