package com.skill8.ecommerce;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return repository.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> filterByPrice(
            @RequestParam double min,
            @RequestParam double max) {
        return repository.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> sortedProducts() {
        return repository.findAllSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> expensiveProducts(@PathVariable double price) {
        return repository.findExpensiveProducts(price);
    }
}