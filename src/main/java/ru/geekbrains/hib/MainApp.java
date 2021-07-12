package ru.geekbrains.hib;

import org.hibernate.Session;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        MySessionFactory mySessionFactory = new MySessionFactory("hibernate.cfg.xml");
        ProductDAO productDAO = new ProductDAO(mySessionFactory.getSessionFactory());

        Product p1 = new Product("Cucumber", 13);
        productDAO.saveOrUpdate(p1);
        Product p2 = new Product("Tomato", 17);
        productDAO.saveOrUpdate(p2);

        Session session = null;
        //
            session = (Session) mySessionFactory.getSessionFactory();
            session.beginTransaction();
            Customer customer1 = session.get(Customer.class, 1);
            List<Product> productsCustomer1 = customer1.getProductsList();
            System.out.println(productsCustomer1);
            session.getTransaction().commit();
        //
//        session = (Session) mySessionFactory.getSessionFactory();
//        session.beginTransaction();
//        Customer customer1 = session.get(Customer.class, 1);
//        List<Product> productsCustomer1 = customer1.getProductsList();
//        System.out.println(productsCustomer1);
//        session.getTransaction().commit();

    }
}
