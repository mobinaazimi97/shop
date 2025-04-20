package com.mftplus.shop.product.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.repository.ProductRepository;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.product.entity.Product;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheableLevel(cacheName = "product_cache")
@CacheEvictLevel(cacheNames = "product_cache")
public class ProductService extends BaseServiceImpl<Product, ProductDto, Long> {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(JpaRepository<Product, Long> repository, BaseMapper<Product, ProductDto> mapper, ProductRepository productRepository, ProductMapper productMapper) {
        super(repository, mapper);
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductDto findByProductGroupId(Long id) {
        Product product = productRepository.findByProductGroupId(id);
        return productMapper.toDto(product);
    }

    public ProductDto findByProductGroupName(String pGroupName) {
        Product product = productRepository.findByProductGroupName(pGroupName);
        return productMapper.toDto(product);
    }

    public ProductDto findByProductNameAndProductGroupName(String pGroupName, String name) {
        Product product = productRepository.findByProductNameAndProductGroupName(pGroupName, name);
        return productMapper.toDto(product);

    }

    @Transactional
    public ProductDto saveWithGroup(ProductDto productDto) {
        // Convert DTO to entity
        Product product = productDto.toEntity(); // ya productMapper.toEntity(productDto)

        // Save entity (with productGroup)
        Product saved = productRepository.save(product);

        // Return DTO
        return productMapper.toDto(saved); // ya convert manually
    }
}
