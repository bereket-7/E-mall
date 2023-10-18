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
}
