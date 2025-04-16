package com.mftplus.shop.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductPropertyValueService {
    private final ProductPropertyValueRepository productPropertyValueRepository;

    public ProductPropertyValueService(ProductPropertyValueRepository productPropertyValueRepository) {
        this.productPropertyValueRepository = productPropertyValueRepository;
    }

    @Transactional
    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
    public ProductPropertyValue save(ProductPropertyValue productPropertyValue) {
        return productPropertyValueRepository.save(productPropertyValue);
    }

    @Transactional
    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
    public ProductPropertyValue update(ProductPropertyValue productPropertyValue) {
        return productPropertyValueRepository.save(productPropertyValue);
    }

    @Transactional
    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
    public List<ProductPropertyValue> findAll() {
        return productPropertyValueRepository.findAll();
    }

    @Transactional
    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
    public ProductPropertyValue findById(Long id) {
        return productPropertyValueRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        productPropertyValueRepository.deleteById(id);
    }
}
