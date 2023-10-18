package com.beki7.ecommerce.controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    int adminlogcheck = 0;
    String usernameforclass = "";
    @RequestMapping(value = {"/","/logout"})
    public String returnIndex() {
        adminlogcheck =0;
        usernameforclass = "";
        return "userLogin";
    }

    @GetMapping("/index")
    public String index(Model model) {
        if(usernameforclass.equalsIgnoreCase(""))
            return "userLogin";
        else {
            model.addAttribute("username", usernameforclass);
            return "index";
        }

    }

}
