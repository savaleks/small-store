package com.savaleks.controller;

import com.savaleks.model.Category;
import com.savaleks.model.Product;
import com.savaleks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String showManageProducts(Model model){
        Product newProduct = new Product();
        newProduct.setSupplierId(1);
        newProduct.setActive(true);
        model.addAttribute("product", newProduct);
        return "manage-product";
    }

    // returning categories for all the request mapping
    @ModelAttribute("categories")
    public List<Category> getListCategory(){
        return categoryService.list();
    }
}
