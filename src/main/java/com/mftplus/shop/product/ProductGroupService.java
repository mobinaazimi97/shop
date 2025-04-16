package com.mftplus.shop.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;

    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @Transactional
    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
    public ProductGroup save(ProductGroup productGroup) {
        return productGroupRepository.save(productGroup);
    }

    @Transactional
    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
    public ProductGroup update(ProductGroup productGroup) {
        return productGroupRepository.save(productGroup);
    }

    @Transactional
    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
    public List<ProductGroup> findAll() {
        return productGroupRepository.findAll();
    }

    @Transactional
    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
    public ProductGroup findById(Long id) {
        return productGroupRepository.findById(id).orElse(null);

    }

    public void deleteById(Long id) {
        productGroupRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
    public ProductGroup findByParent(String name) {
        return productGroupRepository.findByParent(name);
    }

}
