package com.beki7.ecommerce.service;

import java.util.List;

import com.beki7.ecommerce.model.User;
import com.beki7.ecommerce.dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private userDao userDao;

    public List<User> getUsers(){
        return this.userDao.getAllUser();
    }

    public User addUser(User user) {
        return this.userDao.saveUser(user);
    }

    public User checkLogin(String username,String password) {
        return this.userDao.getUser(username, password);
    }
}
