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

        Session session = null;
        try {
            String sql = Files.lines(Paths.get("init.sql")).collect(Collectors.joining(" "));
            session = mySessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();


//            Product p1 = new Product("Cucumber", 13);
//            productDAO.saveOrUpdate(p1);
//            Product p2 = new Product("Tomato", 17);
//            productDAO.saveOrUpdate(p2);
            //
            session = (Session) mySessionFactory.getSessionFactory();
            session.beginTransaction();
            Customer customer1 = session.get(Customer.class, 1);
            List<Product> productsCustomer1 = customer1.getProductsList();
            System.out.println(productsCustomer1);
            session.getTransaction().commit();
            //
            session = (Session) mySessionFactory.getSessionFactory();
            session.beginTransaction();
            Product product2 = session.get(Product.class, 2);
            List<Customer> customersOfProduct = product2.getCustomer();
            System.out.println(customersOfProduct);
            session.getTransaction().commit();


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
