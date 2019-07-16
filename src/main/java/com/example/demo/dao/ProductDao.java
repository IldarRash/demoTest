package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    List<Product> findByNameOrBrand(String name, String brand);
}
