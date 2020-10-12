package com.elenakuropatkina.springbootmarket.services;

import com.elenakuropatkina.springbootmarket.exeptions.NotFoundProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.elenakuropatkina.springbootmarket.models.Product;
import com.elenakuropatkina.springbootmarket.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("Продукт не найден, id: = " + id));
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

}