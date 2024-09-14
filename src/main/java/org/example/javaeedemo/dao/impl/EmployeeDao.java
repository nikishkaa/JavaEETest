package org.example.javaeedemo.dao.impl;

import org.example.javaeedemo.dao.AbstractDao;
import org.example.javaeedemo.entity.Employee;

public class EmployeeDao extends AbstractDao<Integer, Employee> {
    public Employee findByEmail(String email) {
        getSession();
//TODO
        return null;
    }

}
