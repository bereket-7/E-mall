package com.beki7.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.productService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private userService userService;
    @Autowired
    private productService productService;

    @GetMapping("/register")
    public String registerUser()
    {
        return "register";
    }

    @GetMapping("/buy")
    public String buy()
    {
        return "buy";
    }


    @GetMapping("/")
    public String userlogin(Model model) {

        return "userLogin";
    }



}
