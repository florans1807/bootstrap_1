package com.example.demo.service;

import com.example.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User get(int id);
    void add(User user);
    void update(User updatedUser);
    void delete(User user);
    User loadUserByUsername(String login);
}