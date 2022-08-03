package com.firstproject.demo.dao;

import com.firstproject.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUser();

    void userDelete(Long id);

    void registerUser(User user);

    User getUserByCredentials(User user);
}
