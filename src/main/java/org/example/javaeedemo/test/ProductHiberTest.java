package org.example.javaeedemo.test;

import org.example.javaeedemo.dao.ProductsDao;
import org.example.javaeedemo.db.JPAService;
import org.example.javaeedemo.db.config.JpaConfiguration;
import org.example.javaeedemo.entity.Product;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductHiberTest {
    public static void main(String[] args) {
        JpaConfiguration configuration = new JpaConfiguration();
        configuration.setUserName("root");
        configuration.setPassword("");
        configuration.setDriver("com.mysql.jdbc.Driver");
        configuration.setUrl("jdbc:mysql://localhost:3308/j1023_db_new");
        configuration.setPersistentUnit("Employee");


        JPAService.initialize(configuration);

        ProductsDao productsDao = new ProductsDao();

        Product p1 = new Product();
        p1.setName("LG");
        p1.setDescr("TV");
        p1.setInsertTime(new Date());

        Product p2 = new Product();
        p2.setName("SONY");
        p2.setDescr("PS5");
        p2.setInsertTime(new Date());

        productsDao.createAll(Arrays.asList(p1, p2));

       List<Product> products = productsDao.findAll("name like 'SONY%'");
        System.out.println(products.size());
    }
}
