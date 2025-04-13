package com.mftplus.shop.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;

    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    public ProductGroup save(ProductGroup productGroup) {
        return productGroupRepository.save(productGroup);
    }

    public ProductGroup update(ProductGroup productGroup) {
        return productGroupRepository.save(productGroup);
    }

    public List<ProductGroup> findAll() {
        return productGroupRepository.findAll();
    }

    public ProductGroup findById(Long id) {
        return productGroupRepository.findById(id).orElse(null);

    }

    public void deleteById(Long id) {
        productGroupRepository.deleteById(id);
    }
}
