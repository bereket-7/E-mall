package com.beki7.ecommerce.service;

import com.beki7.ecommerce.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.beki7.ecommerce.dao.cartDao;
@Service
public class cartService {
    @Autowired
    private cartDao cartDao;

    public Cart addCart(Cart cart)
    {
        return cartDao.addCart(cart);
    }
    public List<Cart> getCarts(){
        return this.cartDao.getCarts();
    }

}
