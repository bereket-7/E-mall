package com.beki7.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.categoryService;
import com.beki7.ecommerce.service.productService;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private userService userService;
    @Autowired
    private categoryService categoryService;

    @Autowired
    private productService productService;
}