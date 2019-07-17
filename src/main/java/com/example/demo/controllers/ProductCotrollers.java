package com.example.demo.controllers;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilter;
import com.example.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("rest/products/")
public class ProductCotrollers {

    private final ProductService productService;

    public ProductCotrollers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}/")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    @GetMapping(path = "/")
    public List<ProductDto> getProducts(ProductFilter filter) {
        return productService.getProducts(filter);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/")
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}/")
    public void deleteMapping(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping(path = "/leftovers")
    public List<ProductDto> getLeftovers() {
        return productService.getLeftovers();
    }
}
