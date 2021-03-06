package com.example.demo.dao;

import com.example.demo.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAll();
    User get(int id);
    void add(User user);
    void update(User updatedUser);
    void delete(User user);
    User loadUserByUsername(String login);
}
