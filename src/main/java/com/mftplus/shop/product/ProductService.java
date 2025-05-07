package com.mftplus.shop.product;


import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import com.mftplus.shop.productPropertyValue.PropertyValueRepository;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductGroupRepository productGroupRepository;
    private final PropertyValueRepository propertyValueRepository;
    private final GroupPropertyRepository groupPropertyRepository;
    private final PropertyValueMapper propertyValueMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductGroupRepository productGroupRepository, PropertyValueRepository propertyValueRepository, GroupPropertyRepository groupPropertyRepository, PropertyValueMapper propertyValueMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productGroupRepository = productGroupRepository;
        this.propertyValueRepository = propertyValueRepository;
        this.groupPropertyRepository = groupPropertyRepository;
        this.propertyValueMapper = propertyValueMapper;
    }

//    public ProductDto save(ProductDto dto) {
//        // پیدا کردن یا ایجاد گروه محصول (ProductGroup) با شناسه خودکار
//        ProductGroup group = productGroupRepository.findById(dto.getProductGroupId())
//                .orElseGet(() -> {
//                    // اگر گروه محصول پیدا نشد، آن را به طور خودکار ایجاد می‌کنیم
//                    ProductGroup newGroup = new ProductGroup();
//                    newGroup.setTitle("Default Product Group");
//                    return productGroupRepository.save(newGroup); // ذخیره و برگشت
//                });
//
//        // ساخت موجودیت محصول (Product) از DTO
//        Product product = dto.toEntity(group);
//
//        // ذخیره محصول و سپس تبدیل آن به DTO
//        Product savedProduct = productRepository.save(product);
//
//        return productMapper.toDto(savedProduct);
//    }
//
//
//    public ProductDto update(UUID uuid, ProductDto dto) {
//        Product existing = productRepository.findById(uuid)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        ProductGroup group = productGroupRepository.findById(dto.getProductGroupId())
//                .orElseThrow(() -> new RuntimeException("ProductGroup not found from product"));
//
//        existing.setProductName(dto.getName());
//        existing.setPrice(dto.getPrice());
//        existing.setDescription(dto.getDescription());
//        existing.setProductGroup(group);
//
//        List<PropertyValue> propertyValues = buildPropertyValues(dto, existing);
//        existing.getPropertyValues().clear();
//        existing.getPropertyValues().addAll(propertyValues);
//
//        Product updated = productRepository.save(existing);
//        return productMapper.toDto(updated);
//    }
//
//    public ProductDto findById(UUID uuid) {
//        return productRepository.findById(uuid)
//                .map(productMapper::toDto)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//    }
//
//    public List<ProductDto> findAll() {
//        return productRepository.findAll().stream()
//                .map(productMapper::toDto)
//                .toList();
//    }
//
//    private List<PropertyValue> buildPropertyValues(ProductDto dto, Product product) {
//        if (dto.getPropertyValues() == null) return List.of();
//
//        return dto.getPropertyValues().stream()
//                .map(propertyValueDto -> {
//                    PropertyValue propertyValue = propertyValueMapper.toEntity(propertyValueDto);
//                    propertyValue.setProduct(product);
//                    propertyValue.setUuid(propertyValueDto.getId() != null ? propertyValueDto.getId() : UUID.randomUUID());
//
//                    // resolve GroupProperty
//                    GroupProperty groupProperty = groupPropertyRepository.findById(propertyValueDto.getGroupPropertyId())
//                            .orElseThrow(() -> new RuntimeException("GroupProperty not found"));
//                    propertyValue.setGroupProperty(groupProperty);
//
//                    return propertyValue;
//                })
//                .toList();
//    }
//
//    public void logicalRemove(UUID uuid) {
//        productRepository.logicalRemove(uuid);
//    }
}
