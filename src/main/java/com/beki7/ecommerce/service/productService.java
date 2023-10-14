package com.beki7.ecommerce.service;

import java.util.List;

import com.beki7.ecommerce.model.Product;
import com.beki7.ecommerce.dao.productDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class productService {
    @Autowired
    private productDao productDao;

    public List<Product> getProducts(){
        return this.productDao.getProducts();
    }

}
