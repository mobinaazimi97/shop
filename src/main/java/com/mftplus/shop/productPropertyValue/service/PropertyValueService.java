package com.mftplus.shop.productPropertyValue.service;

import com.mftplus.shop.productGroup.ProductGroupService;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.productPropertyValue.entity.PropertyValue;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import com.mftplus.shop.productPropertyValue.repository.PropertyValueRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PropertyValueService {
    private final PropertyValueRepository propertyValueRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final UuidMapper uuidMapper;

    public PropertyValueService(PropertyValueRepository propertyValueRepository, PropertyValueMapper propertyValueMapper, UuidMapper uuidMapper, ProductGroupService productGroupService) {
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

    @Transactional
    public PropertyValueDto update(UUID uuid, PropertyValueDto dto) {
        // تبدیل UUID به ID واقعی
        Long id = uuidMapper.map(uuid, "PropertyValue");

        // دریافت entity موجود
        PropertyValue entity = propertyValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PropertyValue not found"));

        // به‌روزرسانی فیلدها
        entity.setValue(dto.getValue());

        // ذخیره تغییرات
        PropertyValue updated = propertyValueRepository.save(entity);

        // تبدیل به DTO و بازگشت
        return propertyValueMapper.toDto(updated, "PropertyValue");
    }

    @Transactional
    public List<PropertyValueDto> findAll() {
        return propertyValueRepository.findAll()
                .stream()
                .map(g -> propertyValueMapper.toDto(g, "PropertyValue"))
                .collect(Collectors.toList());
    }

    @Transactional
    public PropertyValueDto getByUuid(UUID uuid) {
        Long propertyValueId = uuidMapper.map(uuid, "PropertyValue"); // تبدیل UUID به Long ID
        PropertyValue propertyValue = propertyValueRepository.findById(propertyValueId)
                .orElseThrow(() -> new EntityNotFoundException("PropertyValue not found for UUID"));

        return propertyValueMapper.toDto(propertyValue, "PropertyValue"); // تبدیل entity به DTO با UUID
    }

    public void logicalRemove(UUID uuid) {
        Long propertyValueId = uuidMapper.map(uuid, "PropertyValue");
        propertyValueRepository.logicalRemove(propertyValueId);
    }
}
