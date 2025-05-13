package com.mftplus.shop.productPropertyValue;

import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.product.ProductRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PropertyValueService {
    private final PropertyValueRepository propertyValueRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final UuidMapper uuidMapper;

    public PropertyValueService(PropertyValueRepository propertyValueRepository, PropertyValueMapper propertyValueMapper, UuidMapper uuidMapper) {
        this.propertyValueRepository = propertyValueRepository;
        this.propertyValueMapper = propertyValueMapper;
        this.uuidMapper = uuidMapper;
    }


    @Transactional
    public PropertyValueDto save(PropertyValueDto propertyValueDto) {
        PropertyValue entity = propertyValueMapper.toEntity(propertyValueDto, "PropertyValue");

        PropertyValue savedPropertyValue = propertyValueRepository.save(entity);

        // تبدیل Entity ذخیره‌شده به DTO و بازگشت آن
        return propertyValueMapper.toDto(savedPropertyValue, "PropertyValue");
    }

//    @Transactional
//    public PropertyValueDto save(PropertyValueDto propertyValueDto) {
//        // تبدیل PropertyValueDto به PropertyValue entity
//        PropertyValue propertyValue = propertyValueMapper.toEntity(propertyValueDto, "PropertyValue");
//
//        // تنظیم Product از طریق UUID
//        if (propertyValueDto.getId() != null) {
//            Long propertyValueId = uuidMapper.map(propertyValueDto.getId(), "Product");
//            PropertyValue propertyValue1 = propertyValueRepository.findById(propertyValueId)
//                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//            propertyValue.setValue(propertyValue.getValue());
//            propertyValue.setDeleted(false);
//            propertyValueRepository.save(propertyValue);
//        }
//
//        PropertyValue savedPropertyValue = propertyValueRepository.save(propertyValue);
//
//        // تبدیل PropertyValue entity به PropertyValueDto
//        return propertyValueMapper.toDto(savedPropertyValue, "PropertyValue");
//    }
    @Transactional
    public List<PropertyValueDto> findAll() {
        return propertyValueRepository.findAll()
                .stream()
                .map(g -> propertyValueMapper.toDto(g, "PropertyValue"))
                .collect(Collectors.toList());
    }

    public void logicalRemove(Long id) {
        propertyValueRepository.logicalRemove(id);
    }
}
