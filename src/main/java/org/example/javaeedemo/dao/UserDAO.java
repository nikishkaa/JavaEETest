package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.User;

import java.util.Set;

public interface UserDAO {

    User findByEmail(String email);

    boolean createUser(User user);

    boolean activate(User user);

    Set<User> findAllUsers();
}
