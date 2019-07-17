package com.example.demo.services;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilter;
import com.example.demo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductDto> getProducts(ProductFilter productFilter) {
        log.info("Get products");
        return productDao.findByNameOrBrand(
                productFilter.getName(),
                productFilter.getBrand()).stream()
                                         .map(ProductDto::new)
                                         .collect(Collectors.toList());
    }

    @Transactional
    public void createProduct(ProductDto productDto) {
        log.info("Create product");
        productDao.save(convertToProduct(productDto, true));
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("Update product");
        return Optional.of(productDao.save(convertToProduct(productDto, false)))
                       .map(ProductDto::new)
                       .orElseThrow(RuntimeException::new);
    }


    @Transactional
    public void deleteProduct(Long id) {
        /*Product product = productDao.findById(id).orElseThrow(RuntimeException::new);
        product.setIsDeleted(true);
        productDao.save(product);*/ //todo сделать на удаление через поле
        log.info("Delete product");
        productDao.deleteById(id);
    }

    public ProductDto findProduct(Long id) {
        log.info("find product");
        return productDao.findById(id)
                         .map(ProductDto::new)
                         .orElseThrow(RuntimeException::new);
    }

    public List<ProductDto> getLeftovers() {
        log.info("get leftovers");
        return productDao.findByQuantity().stream()
                                          .map(ProductDto::new)
                                          .collect(Collectors.toList());
    }

    private Product convertToProduct(ProductDto productDto, boolean create) {
        Product product = new Product();
        if (!create)
            product.setId(productDto.getId());
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setIsDeleted(false);

        return product;
    }
}
