package com.example.demo.controllers;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilter;
import com.example.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("rest/products/")
public class ProductCotrollers {

    private final ProductService productService;

    public ProductCotrollers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}/")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    @GetMapping(value = "/")
    public List<ProductDto> getProducts(ProductFilter filter) {
        return productService.getProducts(filter);
    }

    @PostMapping("/")
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
    }

    @DeleteMapping("/{id}/")
    public void deleteMapping(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/leftovers")
    public List<ProductDto> getLeftovers() {
        return productService.getLeftovers();
    }
}
