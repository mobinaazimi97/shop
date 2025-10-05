package com.mftplus.shop.groupProperty;


import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.groupProperty.mapper.GroupPropertyMapper;
import com.mftplus.shop.productPropertyValue.entity.PropertyValue;
import com.mftplus.shop.productPropertyValue.repository.PropertyValueRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class GroupPropertyService {

    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;

    private final PropertyValueRepository propertyValueRepository;
    private final UuidMapper uuidMapper;

    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper, PropertyValueRepository propertyValueRepository, UuidMapper uuidMapper) {
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
        this.propertyValueRepository = propertyValueRepository;
        this.uuidMapper = uuidMapper;
    }


    @Transactional
    public GroupPropertyDto save(GroupPropertyDto groupPropertyDto) {
        // تبدیل GroupPropertyDto به GroupProperty Entity
        GroupProperty entity = groupPropertyMapper.toEntity(groupPropertyDto, "GroupProperty");
        if (groupPropertyDto.getPropertyValues() != null) {
            PropertyValueDto propertyValueDto = groupPropertyDto.getPropertyValues().get(0);
            if (propertyValueDto.getId() != null) {
                Long propertyValueId = uuidMapper.map(propertyValueDto.getId(), "PropertyValue");
                PropertyValue propertyValue = propertyValueRepository.findById(propertyValueId)
                        .orElseThrow(() -> new EntityNotFoundException("not found"));
                entity.setPropertyValues(List.of(propertyValue));
            } else {
                PropertyValue newPropertyValue = new PropertyValue();
                newPropertyValue.setValue(propertyValueDto.getValue());
                newPropertyValue.setDeleted(false);
                propertyValueRepository.save(newPropertyValue);

                entity.setPropertyValues(List.of(newPropertyValue));
            }
        }
        GroupProperty saved = groupPropertyRepository.save(entity);
        return groupPropertyMapper.toDto(saved, "GroupProperty");

    }

    @Transactional
    public GroupPropertyDto update(UUID uuid, GroupPropertyDto dto) {
        Long entityId = uuidMapper.map(uuid, "GroupProperty");
        GroupProperty entity = groupPropertyRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));

        // به‌روزرسانی فیلدهای سطح بالا (مثل groupName)
        groupPropertyMapper.updateFromDto(dto, entity, "GroupProperty");

        // بررسی و به‌روزرسانی propertyValues
        if (dto.getPropertyValues() != null) {
            List<PropertyValue> updatedPropertyValues = new ArrayList<>();

            for (PropertyValueDto pvDto : dto.getPropertyValues()) {
                PropertyValue propertyValue;

                if (pvDto.getId() != null) {
                    Long pvId = uuidMapper.map(pvDto.getId(), "PropertyValue");
                    propertyValue = propertyValueRepository.findById(pvId)
                            .orElseThrow(() -> new EntityNotFoundException("PropertyValue not found with id: " + pvDto.getId()));
                } else {
                    propertyValue = new PropertyValue();
                    propertyValue.setValue(pvDto.getValue());
                    propertyValue.setDeleted(false);
                    propertyValueRepository.save(propertyValue);
                }

                updatedPropertyValues.add(propertyValue);
            }

            entity.setPropertyValues(updatedPropertyValues);
        }

        GroupProperty saved = groupPropertyRepository.save(entity);
        return groupPropertyMapper.toDto(saved, "GroupProperty");
    }

    public void logicalRemove(UUID uuid) {
        Long groupPropertyId = uuidMapper.map(uuid, "GroupProperty");
        groupPropertyRepository.logicalRemove(groupPropertyId);
    }

    @Transactional
    public List<GroupPropertyDto> findAll() {
        return groupPropertyRepository.findAll()
                .stream()
                .map(g -> groupPropertyMapper.toDto(g, "GroupProperty"))
                .collect(Collectors.toList());
    }

    @Transactional
    public GroupPropertyDto getByUuid(UUID uuid) {
        Long groupPropertyId = uuidMapper.map(uuid, "GroupProperty");
        GroupProperty groupProperty = groupPropertyRepository.findById(groupPropertyId)
                .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found for UUID"));
        return groupPropertyMapper.toDto(groupProperty, "GroupProperty");
    }

    @Transactional
    public List<GroupPropertyDto> getGroupNameAndPropertyValue(String value, String groupName) {
        List<GroupProperty> result = groupPropertyRepository.getGroupNameAndPropertyValue(value, groupName);
        return groupPropertyMapper.toDtoList(result, "GroupProperty");
    }

}
