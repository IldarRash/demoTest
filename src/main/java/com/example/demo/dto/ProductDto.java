package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Data;

import java.util.Objects;

@Data
public class ProductDto extends Dto<Product> {
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private Integer quantity;

    public ProductDto(Product product) {
        super(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
