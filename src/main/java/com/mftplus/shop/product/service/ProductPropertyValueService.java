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
public class ProductPropertyValueService extends BaseServiceImpl<ProductPropertyValue,ProductPropertyValueDto,Long> {
    private final ProductPropertyValueMapper productPropertyValueMapper;
    private final ProductPropertyValueRepository productPropertyValueRepository;

    public ProductPropertyValueService(JpaRepository<ProductPropertyValue, Long> repository, BaseMapper<ProductPropertyValue, ProductPropertyValueDto> mapper, ProductPropertyValueRepository productPropertyValueRepository, ProductPropertyValueMapper productPropertyValueMapper) {
        super(repository, mapper);
        this.productPropertyValueRepository = productPropertyValueRepository;
        this.productPropertyValueMapper = productPropertyValueMapper;
    }

//    @Transactional
//    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
//    //mapper:
//    public ProductPropertyValueDto save(ProductPropertyValueDto productPropertyValueDto) {
//        ProductPropertyValue productPropertyValue = productPropertyValueRepository.save(productPropertyValueMapper.toEntity(productPropertyValueDto));
//        return productPropertyValueMapper.toDto(productPropertyValue);
//    }
////    public ProductPropertyValue save(ProductPropertyValue productPropertyValue) {
////        return productPropertyValueRepository.save(productPropertyValue);
////    }
//
//    @Transactional
//    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
//    //mapper
//    public ProductPropertyValueDto update(Long id, ProductPropertyValueDto productPropertyValueDto) {
//        ProductPropertyValue productPropertyValue = productPropertyValueRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("Product Property Value Not Found!"));
//        productPropertyValueMapper.updateFromDto(productPropertyValueDto, productPropertyValue);
//        return productPropertyValueMapper.toDto(productPropertyValueRepository.save(productPropertyValue));

//    public ProductPropertyValue update(ProductPropertyValue productPropertyValue) {
//        return productPropertyValueRepository.save(productPropertyValue);
//    }
//
//    @Transactional
//    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
//    public List<ProductPropertyValue> findAll() {
//        return productPropertyValueRepository.findAll();
//    }
//
//    @Transactional
//    @CacheEvict(cacheNames = "product_properties_values", allEntries = true)
//    public ProductPropertyValue findById(Long id) {
//        return productPropertyValueRepository.findById(id).orElse(null);
//    }
//
//    public void deleteById(Long id) {
//        productPropertyValueRepository.deleteById(id);
//    }
}
