package com.example.demo.controllers;

import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExelProductController {

    private final ProductService productService;

    public ExelProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/download")
    public String download(Model model) {

        model.addAttribute("products", productService.getLeftovers());
        return "";
    }

}
