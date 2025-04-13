package com.mftplus.shop.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);

    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteBiId(Long id) {
        productRepository.deleteById(id);
    }
}
