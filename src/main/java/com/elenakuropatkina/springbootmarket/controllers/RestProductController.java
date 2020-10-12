package com.elenakuropatkina.springbootmarket.controllers;

import com.elenakuropatkina.springbootmarket.exeptions.NotFoundProductException;
import com.elenakuropatkina.springbootmarket.models.Product;
import com.elenakuropatkina.springbootmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class RestProductController {

    private ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOneProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productService.saveOrUpdate(product);
    }


    @DeleteMapping("/{id}")
    public String deleteOneProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "OK";
    }


    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        if (product.getId() == null || !productService.existsById(product.getId())) {
            throw new NotFoundProductException("Продукт не найден, id: " + product.getId());
        }
        return new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(NotFoundProductException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}



