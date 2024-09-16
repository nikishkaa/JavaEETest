package org.example.javaeedemo.dao;

import org.example.javaeedemo.db.AbstractJpaDAO;
import org.example.javaeedemo.model.User;

import java.sql.Timestamp;
import java.util.Objects;

public class UsersDao extends AbstractJpaDAO<Integer, User> {
    public User findByEmail(String email) {
        return findFirst(String.format("email = '%s'", email));
    }


    public boolean activate(final String email) {
        // set IS_ACTIVE = 'Y' & UPD_TS - Current
        User user = findByEmail(email);
        user.setActive(true);
        user.setUpdatedTs(new Timestamp(System.currentTimeMillis()));

        return !Objects.isNull(update(user));
    }

}
