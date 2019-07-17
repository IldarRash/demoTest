package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ProductDto extends Dto<Product> {
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private Integer quantity;

    public ProductDto(Product product) {
        super(product);
    }
}
