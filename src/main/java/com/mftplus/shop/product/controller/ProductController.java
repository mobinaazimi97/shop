package com.mftplus.shop.product.controller;

import com.mftplus.shop.product.dto.ProductDto;

import com.mftplus.shop.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/search")
    public ProductDto search(
            @RequestParam(required = false) String pGroupName,
            @RequestParam(required = false) String name) throws Exception {
        // Agar `name` ya `groupName` vase search mizari, query ro invoke mikonim
        if (name != null && pGroupName != null) {
            return productService.findByProductNameAndProductGroupName(pGroupName, name);
        }else {
            throw new Exception("No products found for provided filters.");

        }
        // Baghie queries ham mitooni zad (masalan, baraay groupName faqat)
//        return productService.findByProductGroupName(pGroupName);
    }
}
