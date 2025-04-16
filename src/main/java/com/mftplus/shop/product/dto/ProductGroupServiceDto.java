package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.ProductGroup;
import com.mftplus.shop.product.ProductGroupRepository;
import com.mftplus.shop.product.mapper.ProductGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupServiceDto {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;

    public ProductGroupServiceDto(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
    }

    // Create or Update ProductGroup

    public ProductGroupDto save(ProductGroupDto dto) {
        ProductGroup entity = productGroupMapper.toEntity(dto);
        ProductGroup saved = productGroupRepository.save(entity);
        return productGroupMapper.toDto(saved);
    }


    public ProductGroupDto update(Long id, ProductGroupDto dto) {
        ProductGroup existing = productGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductGroup not found"));
        ProductGroup updatedEntity = productGroupMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        ProductGroup updated = productGroupRepository.save(updatedEntity);
        return productGroupMapper.toDto(updated);
    }

    public List<ProductGroupDto> findAll() {
        return productGroupMapper.toDtoList(productGroupRepository.findAll());
    }

    public ProductGroupDto findById(Long id) {
        return productGroupRepository.findById(id)
                .map(productGroupMapper::toDto)
                .orElseThrow(() -> new RuntimeException("ProductGroup not found"));
    }
    public void logicalRemove(Long id){
        productGroupRepository.logicalRemove(id);
    }
//by dto
//    public ProductGroupDto saveProductGroup(ProductGroupDto productGroupDto) {
//        ProductGroup productGroup = productGroupDto.getProductGroup(); // Convert DTO to Entity
//        ProductGroup savedProductGroup = productGroupRepository.save(productGroup); // Save to DB
//        return ProductGroupDto.from(savedProductGroup); // Convert saved entity to DTO
//    }
//
//    // Get all ProductGroups
//    public List<ProductGroupDto> getAllProductGroups() {
//        List<ProductGroup> productGroups = productGroupRepository.findAll();
//        return productGroups.stream()
//                .map(ProductGroupDto::from) // Convert each entity to DTO
//                .collect(Collectors.toList());
//    }
//
//    // Get ProductGroup by ID
//    public ProductGroupDto getProductGroupById(Long id) {
//        Optional<ProductGroup> productGroup = productGroupRepository.findById(id);
//        return productGroup.map(ProductGroupDto::from).orElse(null); // Return DTO or null if not found
//    }
//
//    // Delete ProductGroup by ID
//    public void deleteProductGroup(Long id) {
//        productGroupRepository.logicalRemove(id);
//    }
}
