package org.example.javaeedemo.test;

import org.example.javaeedemo.dao.impl.EmployeeDao;
import org.example.javaeedemo.db.JPAService;
import org.example.javaeedemo.db.config.JpaConfiguration;
import org.example.javaeedemo.entity.Employee;

import java.sql.Timestamp;
import java.util.Date;

public class NewHiberTest {
    public static void main(String[] args) {
        JpaConfiguration configuration = new JpaConfiguration();
        configuration.setUserName("root");
        configuration.setPassword("");
        configuration.setDriver("com.mysql.jdbc.Driver");
        configuration.setUrl("jdbc:mysql://localhost:3308/j1023_db_new");
        configuration.setPersistentUnit("Employee");


        JPAService.initialize(configuration);

        EmployeeDao employeeDao = new EmployeeDao();


        Employee employee1 = new Employee();
        employee1.setName("John");
        employee1.setRole("Manager");
        employee1.setInsertTime(new Date());

        employeeDao.create(employee1);

        employeeDao.findAll().forEach(employee -> {
            System.out.println(employee);
        });
    }
}
