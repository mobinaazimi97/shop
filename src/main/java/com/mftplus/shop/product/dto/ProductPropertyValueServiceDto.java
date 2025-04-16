package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.ProductPropertyValue;
import com.mftplus.shop.product.ProductPropertyValueRepository;
import com.mftplus.shop.product.mapper.ProductPropertyValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyValueServiceDto {
private final ProductPropertyValueRepository productPropertyValueRepository;
private final ProductPropertyValueMapper productPropertyValueMapper;

    public ProductPropertyValueServiceDto(ProductPropertyValueRepository productPropertyValueRepository, ProductPropertyValueMapper productPropertyValueMapper) {
        this.productPropertyValueRepository = productPropertyValueRepository;
        this.productPropertyValueMapper = productPropertyValueMapper;
    }

    public ProductPropertyValueDto save(ProductPropertyValueDto dto) {
        ProductPropertyValue entity = productPropertyValueMapper.toEntity(dto);
        ProductPropertyValue saved = productPropertyValueRepository.save(entity);
        return productPropertyValueMapper.toDto(saved);
    }

    public ProductPropertyValueDto update(Long id, ProductPropertyValueDto dto) {
        ProductPropertyValue existing = productPropertyValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductPropertyValue not found"));
        ProductPropertyValue updatedEntity = productPropertyValueMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        ProductPropertyValue updated = productPropertyValueRepository.save(updatedEntity);
        return productPropertyValueMapper.toDto(updated);
    }

    public List<ProductPropertyValueDto> findAll() {
        return productPropertyValueMapper.toDtoList(productPropertyValueRepository.findAll());
    }

    public ProductPropertyValueDto findById(Long id) {
        return productPropertyValueRepository.findById(id)
                .map(productPropertyValueMapper::toDto)
                .orElseThrow(() -> new RuntimeException("ProductPropertyValue not found"));
    }
    public void logicalRemove(Long id){
        productPropertyValueRepository.logicalRemove(id);
    }
//by dto
//    public ProductPropertyValueDto saveProductPropertyValueDto(ProductPropertyValueDto productPropertyValueDto) {
//        ProductPropertyValue productPropertyValue = productPropertyValueDto.getProductPropertyValue(); // Convert DTO to Entity
//        ProductPropertyValue savedProductPropertyValue = productPropertyValueRepository.save(productPropertyValue); // Save to DB
//        return productPropertyValueDto; // Convert saved entity to DTO
//    }
}
