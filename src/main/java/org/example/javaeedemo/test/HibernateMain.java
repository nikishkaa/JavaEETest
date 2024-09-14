package org.example.javaeedemo.test;

import java.util.Date;

import org.example.javaeedemo.entity.Employee;
import org.example.javaeedemo.utils.HibernateUtil;
import org.hibernate.Session;


public class HibernateMain {

    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setName("Pankaj");
        emp.setRole("CEO");
        emp.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID=" + emp.getId());


        // get Employee by id
        Session session1 = HibernateUtil.getSessionFactory().getCurrentSession();
        session1.beginTransaction();

        Employee emp1 = (Employee) session1.get(Employee.class, 19);
        session1.save(emp1);
        System.out.println(emp1);


        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }

}
