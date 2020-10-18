package com.elenakuropatkina.springbootmarket.controllers;

import com.elenakuropatkina.springbootmarket.utils.ProductFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.elenakuropatkina.springbootmarket.models.Product;
import com.elenakuropatkina.springbootmarket.services.ProductService;

import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model, @RequestParam Map<String, String> requestParamMap) {
        Integer pageNumber = Integer.parseInt(requestParamMap.getOrDefault("page", "1"));
        ProductFilter productFilter = new ProductFilter(requestParamMap);
        Page<Product> products = productService.findAll(productFilter.getSpecification(), pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("filter", productFilter.getFilter().toString());
        return "products";

    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product productModify) {
        productService.saveOrUpdate(productModify);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}