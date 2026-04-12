package com.klu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // READ ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // READ BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
            existing.setImageUrl(product.getImageUrl());
            return productRepository.save(existing);
        }
        return null;
    }

    // DELETE
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
