package com.beki7.ecommerce.controller;

import com.beki7.ecommerce.model.Product;
import com.beki7.ecommerce.model.User;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.beki7.ecommerce.service.userService;
import com.beki7.ecommerce.service.categoryService;
import com.beki7.ecommerce.service.productService;
import org.springframework.web.servlet.ModelAndView;
import com.beki7.ecommerce.model.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;


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
            return "adminHome.jsp";
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
            ModelAndView mv = new ModelAndView("adminHome.jsp");
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
    @GetMapping("categories")
    public ModelAndView getcategory() {
        if(adminlogcheck==0){
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        }
        else {
            ModelAndView mView = new ModelAndView("categories");
            List<Category> categories = this.categoryService.getCategories();
            mView.addObject("categories", categories);
            return mView;
        }
    }
    @RequestMapping(value = "categories",method = RequestMethod.POST)
    public String addCategory(@RequestParam("categoryname") String category_name)
    {
        System.out.println(category_name);

        Category category =  this.categoryService.addCategory(category_name);
        if(category.getName().equals(category_name)) {
            return "redirect:categories";
        }else {
            return "redirect:categories";
        }
    }
    @GetMapping("categories/delete")
    public ModelAndView removeCategoryDb(@RequestParam("id") int id)
    {
        this.categoryService.deleteCategory(id);
        ModelAndView mView = new ModelAndView("forward:/categories");
        return mView;
    }

    @GetMapping("categories/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryname)
    {
        Category category = this.categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }


    //*****  Product management ********//

    @GetMapping("products")
    public ModelAndView getproduct() {
        if(adminlogcheck==0){
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        }
        else {
            ModelAndView mView = new ModelAndView("products");
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("message", "No products are available");
            } else {
                mView.addObject("products", products);
            }
            return mView;
        }

    }
    @GetMapping("products/add")
    public ModelAndView addProduct() {
        ModelAndView mView = new ModelAndView("productsAdd");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories",categories);
        return mView;
    }
    @RequestMapping(value = "products/add",method=RequestMethod.POST)
    public String addProduct(@RequestParam("name") String name,@RequestParam("categoryid") int categoryId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("productImage") String productImage) {
        System.out.println(categoryId);
        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(categoryId);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);
        this.productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("products/update/{id}")
    public ModelAndView updateproduct(@PathVariable("id") int id) {

        ModelAndView mView = new ModelAndView("productsUpdate");
        Product product = this.productService.getProduct(id);
        List<Category> categories = this.categoryService.getCategories();

        mView.addObject("categories",categories);
        mView.addObject("product", product);
        return mView;
    }
    @RequestMapping(value = "products/update/{id}",method=RequestMethod.POST)
    public String updateProduct(@PathVariable("id") int id ,@RequestParam("name") String name,@RequestParam("categoryid") int categoryId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("productImage") String productImage)
    {
        return "redirect:/admin/products";
    }
    @GetMapping("products/delete")
    public String removeProduct(@RequestParam("id") int id)
    {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
    @PostMapping("products")
    public String postproduct() {
        return "redirect:/admin/categories";
    }
    @GetMapping("customers")
    public ModelAndView getCustomerDetail() {
        if(adminlogcheck==0){
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        }
        else {
            ModelAndView mView = new ModelAndView("displayCustomers");
            List<User> users = this.userService.getUsers();
            mView.addObject("customers", users);
            return mView;
        }
    }
    @GetMapping("profileDisplay")
    public String profileDisplay(Model model) {
        String displayUsername, displayPassword, displayEmail, displayAddress;
        String usernameForClass = "username";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Emall", "postgres", "password");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, usernameForClass);
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                int userId = rst.getInt(1);
                displayUsername = rst.getString(2);
                displayEmail = rst.getString(3);
                displayPassword = rst.getString(4);
                displayAddress = rst.getString(5);
                model.addAttribute("userId", userId);
                model.addAttribute("username", displayUsername);
                model.addAttribute("email", displayEmail);
                model.addAttribute("password", displayPassword);
                model.addAttribute("address", displayAddress);
            }

            rst.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Hello");
        return "updateProfile";
    }
    @RequestMapping(value = "updateuser",method=RequestMethod.POST)
    public String updateUserProfile(@RequestParam("userid") int userid,@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address)

    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Emall", "postgres", "password");
            PreparedStatement pst = con.prepareStatement("update users set username= ?,email = ?,password= ?, address= ? where uid = ?;");
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, address);
            pst.setInt(5, userid);
            int i = pst.executeUpdate();
            usernameforclass = username;
        }
        catch(Exception e)
        {
            System.out.println("Exception:"+e);
        }
        return "redirect:/index";
    }
}
