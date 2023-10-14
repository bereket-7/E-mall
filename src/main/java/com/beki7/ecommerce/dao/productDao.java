package com.beki7.ecommerce.dao;

import java.util.List;

import com.beki7.ecommerce.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class productDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<Product> getProducts(){
        return this.sessionFactory.getCurrentSession().createQuery("from PRODUCT").list();
    }

    @Transactional
    public Product addProduct(Product product) {
        this.sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Transactional
    public Product getProduct(int id) {
        return this.sessionFactory.getCurrentSession().get(Product.class, id);
    }

}
