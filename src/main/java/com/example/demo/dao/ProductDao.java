package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE (:name is null or p.name = :name) and" +
        " (:brand is null or p.brand = :brand)")
    List<Product> findByNameOrBrand(@Param("name") String name, @Param("brand") String brand);


    @Query("SELECT p FROM Product p WHERE (p.quantity < 5)")
    List<Product> findByQuantity();

}
