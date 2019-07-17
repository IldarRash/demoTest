package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductFilter implements Serializable {
    private String name;
    private String brand;

}
