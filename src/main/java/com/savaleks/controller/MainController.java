package com.savaleks.controller;

import com.savaleks.model.Category;
import com.savaleks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("categories", categoryService.list());
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){return "login";}

    @GetMapping("/register")
    public String registerPage(){return "register";}

//    Method to load all the products and based on category
    @GetMapping("/show/all/products")
    public String getAllProducts(Model model){
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("userClickAllProducts", true);

        return "product-list";
    }

    @GetMapping("/show/category/{id}/products")
    public String getProductById(@PathVariable("id") int id, Model model){

        // fetch single category
        Category category = categoryService.get(id);
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("category", category);
        model.addAttribute("UserClickCategoryProducts", true);

        return "product-list";

    }

}
