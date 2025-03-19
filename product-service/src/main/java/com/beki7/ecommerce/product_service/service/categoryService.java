package com.beki7.ecommerce.service;

import com.beki7.ecommerce.dao.categoryDao;
import com.beki7.ecommerce.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService {
    @Autowired
    private categoryDao categoryDao;

    public Category addCategory(String name) {
        return this.categoryDao.addCategory(name);
    }

    public List<Category> getCategories(){
        return this.categoryDao.getCategories();
    }
    public Boolean deleteCategory(int id) {
        return this.categoryDao.deletCategory(id);
    }

    public Category updateCategory(int id,String name) {
        return this.categoryDao.updateCategory(id, name);
    }
    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }
}
