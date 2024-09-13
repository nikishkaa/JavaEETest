package org.example.javaeedemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "NAME", nullable = true, length = 20)
    private String name;

    @Column(name = "ROLE", nullable = true, length = 20)
    private String role;

    @Column(name = "insert_time", nullable = true)
    private Date insertTime;
}
