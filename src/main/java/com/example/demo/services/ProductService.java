package com.example.demo.services;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilter;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductDto> getProducts(ProductFilter productFilter) {
        return productDao.findByNameOrBrand(
                productFilter.getName(),
                productFilter.getBrand()).stream()
                                         .map(ProductDto::new)
                                         .collect(Collectors.toList());
    }

    @Transactional
    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setIsDeleted(false);

        productDao.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productDao.findById(id).orElseThrow(RuntimeException::new);
        product.setIsDeleted(true);
        productDao.save(product);
    }

    public ProductDto findProduct(Long id) {
        return productDao.findById(id)
                         .map(ProductDto::new)
                         .orElseThrow(RuntimeException::new);
    }

    public List<ProductDto> getLeftovers() {
        return new ArrayList<ProductDto>();
    }
}
