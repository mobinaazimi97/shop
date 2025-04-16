package com.mftplus.shop.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @CacheEvict(cacheNames = "product_tbl", allEntries = true)
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Transactional
    @CacheEvict(cacheNames = "product_tbl", allEntries = true)
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(cacheNames = "product_tbl", allEntries = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);

    }

    @Transactional
    @CacheEvict(cacheNames = "product_tbl", allEntries = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteBiId(Long id) {
        productRepository.deleteById(id);
    }
}
