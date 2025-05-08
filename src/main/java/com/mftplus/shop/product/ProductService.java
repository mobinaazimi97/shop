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
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductGroupRepository productGroupRepository;
    private final PropertyValueRepository propertyValueRepository;
    private final GroupPropertyRepository groupPropertyRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final UuidMapper uuidMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductGroupRepository productGroupRepository, PropertyValueRepository propertyValueRepository, GroupPropertyRepository groupPropertyRepository, PropertyValueMapper propertyValueMapper, UuidMapper uuidMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productGroupRepository = productGroupRepository;
        this.propertyValueRepository = propertyValueRepository;
        this.groupPropertyRepository = groupPropertyRepository;
        this.propertyValueMapper = propertyValueMapper;
        this.uuidMapper = uuidMapper;
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        // تبدیل ProductDto به Product entity
        Product product = productMapper.toEntity(productDto, "Product");

        // تنظیم ProductGroup از طریق UUID
        if (productDto.getProductGroupId() != null) {
            Long groupId = uuidMapper.map(productDto.getProductGroupId(), "ProductGroup");
            ProductGroup productGroup = productGroupRepository.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));
            product.setProductGroup(productGroup);
        }

        // تبدیل PropertyValueDto ها به PropertyValue entity و اضافه کردن آن‌ها به Product
        if (productDto.getPropertyValues() != null && !productDto.getPropertyValues().isEmpty()) {
            List<PropertyValue> propertyValues = productDto.getPropertyValues().stream().map(pvDto -> {
                // تبدیل PropertyValueDto به PropertyValue
                PropertyValue value = propertyValueMapper.toEntity(pvDto, "PropertyValue");

                // پیدا کردن GroupProperty از طریق UUID
                Long gpId = uuidMapper.map(pvDto.getGroupPropertyId(), "GroupProperty");
                GroupProperty groupProperty = groupPropertyRepository.findById(gpId)
                        .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));
                value.setGroupProperty(groupProperty);

                // اضافه کردن PropertyValue به Product
                product.addPropertyValue(value);
                return value;
            }).collect(Collectors.toList());
        }

        // ذخیره کردن محصول و مرتبط کردن property ها
        Product saved = productRepository.save(product);

        // تبدیل entity به DTO و برگرداندن نتیجه
        return productMapper.toDto(saved, "Product");
    }

    public void logicalRemove(Long id) {
        productRepository.logicalRemove(id);
    }
}
