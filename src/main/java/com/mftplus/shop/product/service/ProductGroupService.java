package com.mftplus.shop.product.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.product.dto.ProductGroupDto;
import com.mftplus.shop.product.mapper.ProductGroupMapper;
import com.mftplus.shop.product.repository.ProductGroupRepository;
import com.mftplus.shop.product.entity.ProductGroup;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
@CacheableLevel(cacheName = "ProductGroup_cache")
@CacheEvictLevel(cacheNames = "ProductGroup_cache")
public class ProductGroupService extends BaseServiceImpl<ProductGroup, ProductGroupDto,Long> {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;

    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper) {
        super(productGroupRepository, productGroupMapper);
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
    }


//    public ProductGroupService(ProductGroupRepository productGroupRepository) {
//        this.productGroupRepository = productGroupRepository;
//    }

//    @Transactional
//    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
//    public ProductGroup save(ProductGroup productGroup) {
//        return productGroupRepository.save(productGroup);
//    }

//    @Transactional
//    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
//    public ProductGroup update(ProductGroup productGroup) {
//        return productGroupRepository.save(productGroup);
//    }

//    @Transactional
//    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
//    public List<ProductGroup> findAll() {
//        return productGroupRepository.findAll();
//    }

//    @Transactional
//    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
//    public ProductGroup findById(Long id) {
//        return productGroupRepository.findById(id).orElse(null);
//
//    }

//    public void deleteById(Long id) {
//        productGroupRepository.deleteById(id);
//    }

//    @Transactional
//    @CacheEvict(cacheNames = "productGroup_tbl", allEntries = true)
//    public ProductGroup findByParent(String name) {
//        return productGroupRepository.findByParent(name);
//    }

}
