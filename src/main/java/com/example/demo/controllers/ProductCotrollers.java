package com.example.demo.controllers;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilter;
import com.example.demo.model.Product;
import com.example.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        return new ProductDto();
    }

    @GetMapping(value = "/")
    public List<ProductDto> getProducts(ProductFilter filter) {

    }

    @PostMapping("/")
    public void createProduct(ProductDto productDto) {

    }

}
