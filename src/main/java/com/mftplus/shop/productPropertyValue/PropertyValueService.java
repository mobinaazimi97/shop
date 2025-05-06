package com.mftplus.shop.productPropertyValue;

import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.ProductRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PropertyValueService {
    private final PropertyValueRepository propertyValueRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final ProductRepository productRepository;
    private final GroupPropertyRepository groupPropertyRepository;

    public PropertyValueService(PropertyValueRepository propertyValueRepository, PropertyValueMapper propertyValueMapper, ProductRepository productRepository, GroupPropertyRepository groupPropertyRepository) {
        this.propertyValueRepository = propertyValueRepository;
        this.propertyValueMapper = propertyValueMapper;
        this.productRepository = productRepository;
        this.groupPropertyRepository = groupPropertyRepository;
    }

    @Transactional
    public PropertyValueDto save(PropertyValueDto dto) {
        PropertyValue propertyValue = propertyValueMapper.toEntity(dto);
        if (dto.getGroupPropertyName() != null) {
            GroupProperty groupProperty = groupPropertyRepository.findByName(dto.getGroupPropertyName())
                    .orElseThrow(() -> new RuntimeException("GroupProperty not found in propertyValue to save!!"));
            propertyValue.setGroupProperty(groupProperty);
        }
        if (dto.getProductName() != null) {
            Product product = productRepository.findByProductName(dto.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product not found in propertyValue to save!!"));
            propertyValue.setProduct(product);
        }
        return propertyValueMapper.toDto(propertyValueRepository.save(propertyValue));
    }

    public PropertyValueDto update(UUID uuid, PropertyValueDto dto) {
        PropertyValue existPropertyValue = propertyValueRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("PropertyValue not found"));

        existPropertyValue.setValue(dto.getValue());

        if (!existPropertyValue.getProduct().getUuid().equals(dto.getProductId())) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            existPropertyValue.setProduct(product);
        }

        if (!existPropertyValue.getGroupProperty().getUuid().equals(dto.getGroupPropertyId())) {
            GroupProperty gp = groupPropertyRepository.findById(dto.getGroupPropertyId())
                    .orElseThrow(() -> new RuntimeException("GroupProperty not found"));
            existPropertyValue.setGroupProperty(gp);
        }

        return propertyValueMapper.toDto(propertyValueRepository.save(existPropertyValue));
    }

    public PropertyValueDto findById(UUID uuid) {
        return propertyValueRepository.findById(uuid)
                .map(propertyValueMapper::toDto)
                .orElseThrow(() -> new RuntimeException("PropertyValue not found"));
    }

    public List<PropertyValueDto> findAll() {
        return propertyValueRepository.findAll().stream()
                .map(propertyValueMapper::toDto)
                .toList();
    }

    public void logicalRemove(UUID uuid) {
        propertyValueRepository.logicalRemove(uuid);
    }
}
