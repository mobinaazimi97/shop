package com.mftplus.shop.productGroup;


import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.groupProperty.mapper.GroupPropertyMapper;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productGroup.mapper.ProductGroupMapper;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import com.mftplus.shop.productPropertyValue.PropertyValueRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;
    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;
    private final PropertyValueRepository propertyValueRepository;
    private final UuidMapper uuidMapper;


    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper, GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper, PropertyValueRepository propertyValueRepository, UuidMapper uuidMapper) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
        this.propertyValueRepository = propertyValueRepository;
        this.uuidMapper = uuidMapper;
    }


    @Transactional
    public ProductGroupDto save(ProductGroupDto productGroupDto) {
        ProductGroup entity = productGroupMapper.toEntity(productGroupDto, "ProductGroup");

        // 1. تنظیم parent اگر وجود دارد
        if (productGroupDto.getParentId() != null) {
            Long parentId = uuidMapper.map(productGroupDto.getParentId(), "ProductGroup");
            ProductGroup parent = productGroupRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("Parent group not found"));
            entity.setParent(parent);
        }

        // 2. تنظیم groupProperty اگر وجود دارد
        if (productGroupDto.getGroupPropertyDto() != null) {
            GroupPropertyDto groupPropertyDto = productGroupDto.getGroupPropertyDto();

            GroupProperty groupPropertyEntity;

            if (groupPropertyDto.getId() != null) {
                // موجود است، واکشی کن
                Long groupPropertyId = uuidMapper.map(groupPropertyDto.getId(), "GroupProperty");
                groupPropertyEntity = groupPropertyRepository.findById(groupPropertyId)
                        .orElseThrow(() -> new EntityNotFoundException("Group property not found"));
            } else {
                // جدید است، بساز
                groupPropertyEntity = new GroupProperty();
            }

            groupPropertyEntity.setGroupName(groupPropertyDto.getGroupName());
            groupPropertyEntity.setDeleted(false);

            // 3. ذخیره PropertyValues (در صورت وجود)
            List<PropertyValue> propertyValues = new ArrayList<>();
            if (groupPropertyDto.getPropertyValues() != null) {
                for (PropertyValueDto pvDto : groupPropertyDto.getPropertyValues()) {
                    PropertyValue pv;

                    if (pvDto.getId() != null) {
                        Long pvId = uuidMapper.map(pvDto.getId(), "PropertyValue");
                        pv = propertyValueRepository.findById(pvId)
                                .orElseThrow(() -> new EntityNotFoundException("PropertyValue not found"));
                    } else {
                        pv = new PropertyValue();
                    }

                    pv.setValue(pvDto.getValue());
                    pv.setDeleted(false);
                    propertyValueRepository.save(pv);

                    propertyValues.add(pv);
                }
                groupPropertyEntity.setPropertyValues(propertyValues);
            }

            // ذخیره groupProperty
            groupPropertyRepository.save(groupPropertyEntity);
            entity.setGroupProperty(groupPropertyEntity);
        }

        // 4. ذخیره ProductGroup
        ProductGroup saved = productGroupRepository.save(entity);
        return productGroupMapper.toDto(saved, "ProductGroup");
    }

    @Transactional
    public ProductGroupDto updateByUuid(UUID uuid, ProductGroupDto dto) {
        Long entityId = uuidMapper.map(uuid, "ProductGroup");

        ProductGroup entity = productGroupRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found for UUID: " + uuid));

        // به‌روزرسانی فیلدهای ساده (title و ...)
        productGroupMapper.updateFromDto(dto, entity, "ProductGroup");

        // به‌روزرسانی parent (در صورت وجود)
        if (dto.getParentId() != null) {
            Long parentId = uuidMapper.map(dto.getParentId(), "ProductGroup");
            ProductGroup parent = productGroupRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("Parent ProductGroup not found"));
            entity.setParent(parent);
        } else {
            entity.setParent(null);
        }

        // به‌روزرسانی groupProperty (در صورت وجود)
        if (dto.getGroupPropertyDto() != null) {
            GroupPropertyDto groupPropertyDto = dto.getGroupPropertyDto();
            GroupProperty groupProperty;

            if (groupPropertyDto.getId() != null) {
                Long groupPropertyId = uuidMapper.map(groupPropertyDto.getId(), "GroupProperty");
                groupProperty = groupPropertyRepository.findById(groupPropertyId)
                        .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));
            } else {
                groupProperty = new GroupProperty();
            }

            // تنظیم مقادیر groupProperty
            groupProperty.setGroupName(groupPropertyDto.getGroupName());
            groupProperty.setDeleted(false);

            // به‌روزرسانی PropertyValues
            if (groupPropertyDto.getPropertyValues() != null) {
                List<PropertyValue> propertyValues = new ArrayList<>();
                for (PropertyValueDto pvDto : groupPropertyDto.getPropertyValues()) {
                    PropertyValue pv;
                    if (pvDto.getId() != null) {
                        Long pvId = uuidMapper.map(pvDto.getId(), "PropertyValue");
                        pv = propertyValueRepository.findById(pvId)
                                .orElseThrow(() -> new EntityNotFoundException("PropertyValue not found"));
                    } else {
                        pv = new PropertyValue();
                    }

                    pv.setValue(pvDto.getValue());
                    pv.setDeleted(false);
                    propertyValueRepository.save(pv);

                    propertyValues.add(pv);
                }
                groupProperty.setPropertyValues(propertyValues);
            }

            groupPropertyRepository.save(groupProperty);
            entity.setGroupProperty(groupProperty);
        }

        ProductGroup saved = productGroupRepository.save(entity);
        return productGroupMapper.toDto(saved, "ProductGroup");
    }


    public List<ProductGroupDto> findAll() {
        return productGroupRepository.findAll()
                .stream()
                .map(p -> productGroupMapper.toDto(p, "ProductGroup"))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public ProductGroupDto findById(Long id) {
//        ProductGroup productGroup = productGroupRepository.findById(id).orElse(null);
//        return productGroupMapper.toDto(productGroup, "ProductGroup");
//    }

    @Transactional
    public ProductGroupDto getByUuid(UUID uuid) {
        Long productGroupId = uuidMapper.map(uuid, "ProductGroup"); // تبدیل UUID به Long ID
        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found for UUID"));

        return productGroupMapper.toDto(productGroup, "ProductGroup"); // تبدیل entity به DTO با UUID
    }

    @Transactional(readOnly = true)
    public List<ProductGroupDto> findByParentId(UUID parentUuid) {
        Long parentId = uuidMapper.map(parentUuid, "ProductGroup");
        List<ProductGroup> result = productGroupRepository.findByParentId(parentId);
        return productGroupMapper.toDtoList(result, "ProductGroup");
    }

    @Transactional(readOnly = true)
    public List<ProductGroupDto> findByParentTitle(String parentTitle) {
        List<ProductGroup> result = productGroupRepository.findByParentTitle(parentTitle);
        return productGroupMapper.toDtoList(result, "ProductGroup");
    }

//    @Transactional(readOnly = true)
//    public List<ProductGroupDto> findByParentTitle(String title) {
//        Long parentId = uuidMapper.map(parentUuid, "ProductGroup");
//        List<ProductGroup> result = productGroupRepository.findByParentId(parentId);
//        return productGroupMapper.toDtoList(result, "ProductGroup");
//    }


    @Transactional
    public List<ProductGroupDto> getByTitle(String title) {
        return productGroupRepository.findByTitle(title)
                .stream()
                .map(p -> productGroupMapper.toDto(p, "ProductGroup"))
                .collect(Collectors.toList());
//        ProductGroup productGroup = productGroupRepository.findByTitle(title).orElse(null);
//        return productGroupMapper.toDto(productGroup, "ProductGroup");
    }


    public void logicalRemove(UUID uuid) {
        Long productGroupId = uuidMapper.map(uuid, "ProductGroup");

        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found or already deleted: " + productGroupId));
        productGroup.setDeleted(true);
        productGroupRepository.save(productGroup);
    }

    @Transactional(readOnly = true)
    public List<ProductGroupDto> getProductGroupsByGroupNameAndPropertyValue(String groupName, String value) {
        List<GroupProperty> groupProperties = groupPropertyRepository.getGroupNameAndPropertyValue(value, groupName);

        List<ProductGroup> relatedProductGroups = productGroupRepository.findByGroupNameAndPropertyValue(groupName, value);

        return productGroupMapper.toDtoList(relatedProductGroups, "ProductGroup");
    }

    @Transactional(readOnly = true)
    public List<ProductGroupDto> getProductGroupsByGroupNameAndPropertyValueAndTitle(String groupName, String value, String title) {
        List<GroupProperty> groupProperties = groupPropertyRepository.getGroupNameAndPropertyValue(value, groupName);

        List<ProductGroup> relatedProductGroups = productGroupRepository.findByGroupNameAndPropertyValueAndTitle(groupName, value, title);

        return productGroupMapper.toDtoList(relatedProductGroups, "ProductGroup");
    }

}
