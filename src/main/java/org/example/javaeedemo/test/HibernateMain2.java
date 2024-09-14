package org.example.javaeedemo.test;

import java.util.Date;

import org.example.javaeedemo.dao.impl.EmployeeDao;
import org.example.javaeedemo.entity.Employee;
import org.example.javaeedemo.utils.HibernateAnnotationUtil;
import org.example.javaeedemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateMain2 {
    public static EmployeeDao dao = new EmployeeDao();

    public static void main1(String[] args) {
        Transaction txn = null;
        try (Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();) {
            txn = session.beginTransaction();

            Employee employee = session.get(Employee.class, 1);
            txn.commit();

            System.out.println(employee);
        } catch (Exception e) {
            e.printStackTrace();
            if (txn != null) {
                txn.rollback();
            }
        } finally {
            HibernateAnnotationUtil.close();
        }


        // после commit нужно открывать новую транзакцию
//        Employee emp = new Employee();
//        emp.setName("Pankaj");
//        emp.setRole("CEO");
//        emp.setInsertTime(new Date());
//
//        Session session1 = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
//        session1.save(emp);
//        session1.getTransaction().commit();
//        System.out.println("Employee ID=" + emp.getId());


    }


    public static void main(String[] args) {
        Employee emp = dao.getByKey(1);
        System.out.println(emp);
    }

}
