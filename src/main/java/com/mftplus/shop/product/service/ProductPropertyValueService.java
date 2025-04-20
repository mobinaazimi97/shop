package com.mftplus.shop.product.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.repository.ProductPropertyValueRepository;
import com.mftplus.shop.product.dto.ProductPropertyValueDto;
import com.mftplus.shop.product.entity.ProductPropertyValue;
import com.mftplus.shop.product.mapper.ProductPropertyValueMapper;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@CacheableLevel(cacheName = "productPropertyValue_cache")
@CacheEvictLevel(cacheNames = "productPropertyValue_cache")
public class ProductPropertyValueService extends BaseServiceImpl<ProductPropertyValue, ProductPropertyValueDto, Long> {
    private final ProductPropertyValueMapper productPropertyValueMapper;
    private final ProductPropertyValueRepository productPropertyValueRepository;

    public ProductPropertyValueService(JpaRepository<ProductPropertyValue, Long> repository, BaseMapper<ProductPropertyValue, ProductPropertyValueDto> mapper, ProductPropertyValueRepository productPropertyValueRepository, ProductPropertyValueMapper productPropertyValueMapper) {
        super(repository, mapper);
        this.productPropertyValueRepository = productPropertyValueRepository;
        this.productPropertyValueMapper = productPropertyValueMapper;
    }
}
