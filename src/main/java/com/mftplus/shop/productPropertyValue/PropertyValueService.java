package com.mftplus.shop.productPropertyValue;

import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.ProductRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class PropertyValueService {
    private final PropertyValueRepository propertyValueRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final ProductRepository productRepository;
    private final GroupPropertyRepository groupPropertyRepository;
    private final UuidMapper uuidMapper;

    public PropertyValueService(PropertyValueRepository propertyValueRepository, PropertyValueMapper propertyValueMapper, ProductRepository productRepository, GroupPropertyRepository groupPropertyRepository, UuidMapper uuidMapper) {
        this.propertyValueRepository = propertyValueRepository;
        this.propertyValueMapper = propertyValueMapper;
        this.productRepository = productRepository;
        this.groupPropertyRepository = groupPropertyRepository;
        this.uuidMapper = uuidMapper;
    }

    @Transactional
    public PropertyValueDto save(PropertyValueDto propertyValueDto) {
        // تبدیل PropertyValueDto به PropertyValue entity
        PropertyValue propertyValue = propertyValueMapper.toEntity(propertyValueDto, "PropertyValue");

        // تنظیم Product از طریق UUID
        if (propertyValueDto.getProductId() != null) {
            Long productId = uuidMapper.map(propertyValueDto.getProductId(), "Product");
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            propertyValue.setProduct(product);
        }

        // تنظیم GroupProperty از طریق UUID
        if (propertyValueDto.getGroupPropertyId() != null) {
            Long groupPropertyId = uuidMapper.map(propertyValueDto.getGroupPropertyId(), "GroupProperty");
            GroupProperty groupProperty = groupPropertyRepository.findById(groupPropertyId)
                    .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));
            propertyValue.setGroupProperty(groupProperty);
        }

        // ذخیره کردن PropertyValue
        PropertyValue savedPropertyValue = propertyValueRepository.save(propertyValue);

        // تبدیل PropertyValue entity به PropertyValueDto
        return propertyValueMapper.toDto(savedPropertyValue, "PropertyValue");
    }

    public void logicalRemove(Long id) {
        propertyValueRepository.logicalRemove(id);
    }
}
