package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.User;

public interface UserDAO {

    User findByEmail(String email);
    boolean createUser(User user);


}
