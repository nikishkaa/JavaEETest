package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.Role;
import org.example.javaeedemo.model.User;

import java.sql.SQLException;

public interface RoleDao {
    Role findRoleById(int id);

    Role findRoleByUser(User user);


}
