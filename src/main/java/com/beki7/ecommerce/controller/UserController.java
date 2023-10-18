package com.beki7.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.productService;

@Controller
public class UserController {
    @Autowired
    private userService userService;
    @Autowired
    private productService productService;
}
