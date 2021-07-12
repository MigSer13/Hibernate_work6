package ru.geekbrains.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;

    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return  product;
    }

    public List<Product> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("SELECT p FROM Products p", Product.class).getResultList();
        session.getTransaction().commit();
        return  products;
    }

    public void deleteById(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }
    public Product saveOrUpdate(Product product){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        return  product;
    }

}
