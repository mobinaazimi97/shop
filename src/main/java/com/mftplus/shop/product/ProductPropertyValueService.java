package com.mftplus.shop.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyValueService {
    private final ProductPropertyValueRepository productPropertyValueRepository;

    public ProductPropertyValueService(ProductPropertyValueRepository productPropertyValueRepository) {
        this.productPropertyValueRepository = productPropertyValueRepository;
    }

    public ProductPropertyValue save(ProductPropertyValue productPropertyValue) {
        return productPropertyValueRepository.save(productPropertyValue);
    }

    public ProductPropertyValue update(ProductPropertyValue productPropertyValue) {
        return productPropertyValueRepository.save(productPropertyValue);
    }

    public List<ProductPropertyValue> findAll() {
        return productPropertyValueRepository.findAll();
    }

    public ProductPropertyValue findById(Long id) {
        return productPropertyValueRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        productPropertyValueRepository.deleteById(id);
    }
}
