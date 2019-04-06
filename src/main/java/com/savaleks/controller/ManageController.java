package com.savaleks.controller;

import com.savaleks.model.Category;
import com.savaleks.model.Product;
import com.savaleks.service.CategoryService;
import com.savaleks.service.ProductService;
import com.savaleks.utility.FileUploadUtility;
import com.savaleks.validator.ProductValidator;
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
            if (operation.equals("category")){
                model.addAttribute("message", "Category added to database.");
            }
        }
        return "manage-product";
    }

    @PostMapping("/products")
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,
                                          BindingResult result, Model model, HttpServletRequest request){

        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, result);
        } else {
            if (!mProduct.getFile().getOriginalFilename().equals("")){
                new ProductValidator().validate(mProduct, result);
            }
        }

        // check if the form have error
        if (result.hasErrors()){
            model.addAttribute("message", "Validation failed for Product Submission");
            return "manage-product";
        }

        LOGGER.info(mProduct.toString());
        // create or update the product
        if (mProduct.getId() == 0){
            productService.add(mProduct);
        } else {
            productService.update(mProduct);
        }

        if (!mProduct.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/products?operation=product";
    }

    @PostMapping("/product/{id}/activation")
    @ResponseBody
    public String handleProductActivation(@PathVariable("id") int id){
        // get the product from database
        Product product = productService.get(id);
        boolean isActive = product.isActive();

        // on or off product from datatable
        product.setActive(!product.isActive());
        productService.update(product);
        return (isActive)?"You deactivated the product with id " + product.getId():
                            "You activated the product with id " + product.getId();
    }

    @GetMapping("/{id}/product")
    public String showEditProduct(Model model, @PathVariable String id){
        Product newProduct = productService.get(Integer.valueOf(id));
        model.addAttribute("product", newProduct);
        return "manage-product";
    }

    @PostMapping("/category")
    public String handleCategorySubmission(@ModelAttribute Category category){
        LOGGER.info(category.getName()); // null
        categoryService.add(category);
        return "redirect:/manage/products?operation=category";
    }

    // returning categories for all the request mapping
    @ModelAttribute("categories")
    public List<Category> getListCategory(){
        return categoryService.list();
    }

    @ModelAttribute("category")
    public Category getCategory(){return new Category();}
}
