package ru.geekbrains.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDAO {
    private SessionFactory sessionFactory;

    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer findById(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        return  customer;
    }

    public List<Customer> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> сustomers = session.createQuery("SELECT с FROM Сustomer с", Customer.class).getResultList();
        session.getTransaction().commit();
        return  сustomers;
    }

    public void deleteById(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.remove(customer);
        session.getTransaction().commit();
    }
    public Customer saveOrUpdate(Customer customer){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        return  customer;
    }

    public List<Customer> findProductsOfClientById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        List<Customer> customerList = product.getCustomers();
        System.out.println(customerList.stream().iterator().toString());
        session.getTransaction().commit();
        return customerList;
    }

}
