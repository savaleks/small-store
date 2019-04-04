package com.savaleks.controller;

import com.savaleks.model.Category;
import com.savaleks.model.Product;
import com.savaleks.service.CategoryService;
import com.savaleks.service.ProductService;
import com.savaleks.utility.FileUploadUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showManageProducts(@RequestParam(value = "operation", required = false) String operation, Model model){
        Product newProduct = new Product();
        newProduct.setSupplierId(1);
        newProduct.setActive(true);
        model.addAttribute("product", newProduct);

        if (operation != null){
            if (operation.equals("product")){
                model.addAttribute("message", "Product added to database.");
            }
        }
        return "manage-product";
    }

    @PostMapping("/products")
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,
                                          BindingResult result, Model model, HttpServletRequest request){

        // check if the form have error
        if (result.hasErrors()){
            model.addAttribute("message", "Validation failed for Product Submission");
            return "manage-product";
        }

        LOGGER.info(mProduct.toString());

        // create a new product record
        productService.add(mProduct);

        if (!mProduct.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/products?operation=product";
    }

    // returning categories for all the request mapping
    @ModelAttribute("categories")
    public List<Category> getListCategory(){
        return categoryService.list();
    }
}
