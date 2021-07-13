package ru.geekbrains.hib;

import org.hibernate.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        MySessionFactory mySessionFactory = new MySessionFactory("hibernate.cfg.xml");
        ProductDAO productDAO = new ProductDAO(mySessionFactory.getSessionFactory());
        CustomerDAO customerDAO = new CustomerDAO(mySessionFactory.getSessionFactory());

        Session session = null;
        try {
            String sql = Files.lines(Paths.get("init.sql")).collect(Collectors.joining(" "));
            session = mySessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();

            List<Product> productList = productDAO.findProductsOfClientById(2L);
            List<Customer> customerList = customerDAO.findProductsOfClientById(1L);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mySessionFactory.getSessionFactory().close();
            if (session != null) {
                session.close();
            }
        }
    }
}
