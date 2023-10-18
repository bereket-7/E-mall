package com.beki7.ecommerce.controller;

import com.beki7.ecommerce.model.Product;
import com.beki7.ecommerce.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.productService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    @RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
    public ModelAndView userlogin(@RequestParam("username") String username, @RequestParam("password") String pass, Model model, HttpServletResponse res) {

        System.out.println(pass);
        User u = this.userService.checkLogin(username, pass);
        System.out.println(u.getUsername());
        if(u.getUsername().equals(username)) {

            res.addCookie(new Cookie("username", u.getUsername()));
            ModelAndView mView  = new ModelAndView("index");
            mView.addObject("user", u);
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("message", "No products are available");
            } else {
                mView.addObject("products", products);
            }
            return mView;

        }else {
            ModelAndView mView = new ModelAndView("userLogin");
            mView.addObject("message", "Please enter correct email and password");
            return mView;
        }
    }

    @GetMapping("/user/products")
    public ModelAndView getproduct() {

        ModelAndView mView = new ModelAndView("uproduct");

        List<Product> products = this.productService.getProducts();

        if(products.isEmpty()) {
            mView.addObject("message","No products are available");
        }else {
            mView.addObject("products",products);
        }

        return mView;
    }

    @RequestMapping(value = "newuserregister", method = RequestMethod.POST)
    public String newUseRegister(@ModelAttribute User user)
    {
        System.out.println(user.getEmail());
        user.setRole("ROLE_NORMAL");
        this.userService.addUser(user);
        return "redirect:/";
    }



}
