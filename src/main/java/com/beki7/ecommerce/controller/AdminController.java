package com.beki7.ecommerce.controller;
import com.beki7.ecommerce.model.User;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.categoryService;
import com.beki7.ecommerce.service.productService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("login")
    public String adminlogin() {

        return "adminlogin";
    }
    @GetMapping("Dashboard")
    public String adminHome(Model model) {
        if(adminlogcheck==1)
            return "adminHome";
        else
            return "redirect:/admin/login";
    }
    @GetMapping("/loginvalidate")
    public String adminlog(Model model) {

        return "adminlogin";
    }

    @RequestMapping(value = "loginvalidate", method = RequestMethod.POST)
    public ModelAndView adminlogin(@RequestParam("username") String username, @RequestParam("password") String pass) {

        User user=this.userService.checkLogin(username, pass);

        if(user.getRole().equals("ROLE_ADMIN")) {
            ModelAndView mv = new ModelAndView("adminHome");
            adminlogcheck=1;
            mv.addObject("admin", user);
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("adminlogin");
            mv.addObject("message", "Please enter correct username and password");
            return mv;
        }
    }



}
