package com.mftplus.shop.groupProperty;


import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.groupProperty.mapper.GroupPropertyMapper;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GroupPropertyService {

    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;
    private final ProductGroupRepository productGroupRepository;

    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper, ProductGroupRepository productGroupRepository) {
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
        this.productGroupRepository = productGroupRepository;
    }


    @Transactional
    public GroupPropertyDto save(GroupPropertyDto dto) {
        GroupProperty groupProperty = groupPropertyMapper.toEntity(dto);

        if (dto.getProductGroupTitle() != null) {
            ProductGroup productGroup = productGroupRepository.findByTitle(dto.getProductGroupTitle())
                    .orElseThrow(() -> new RuntimeException("Product group not found"));
            groupProperty.setProductGroup(productGroup);
        }

        return groupPropertyMapper.toDto(groupPropertyRepository.save(groupProperty));

    }

    public GroupPropertyDto update(UUID uuid, GroupPropertyDto dto) {
        GroupProperty entity = groupPropertyRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("GroupProperty not found to update"));
        entity.setGroupName(dto.getName());
        if (dto.getProductGroupId() != null) {
            ProductGroup productGroup = productGroupRepository.findById(dto.getProductGroupId())
                    .orElseThrow(() -> new RuntimeException("ProductGroup not found in GroupProperty for update"));
            entity.setProductGroup(productGroup);
        }
        GroupProperty updated = groupPropertyRepository.save(entity);
        return groupPropertyMapper.toDto(updated);
    }

    public List<GroupPropertyDto> findAll() {
        return groupPropertyRepository.findAll()
                .stream()
                .filter(g -> !g.isDeleted())
                .map(groupPropertyMapper::toDto)
                .toList();
    }

    public GroupPropertyDto findById(UUID uuid) {
        GroupProperty groupProperty = groupPropertyRepository.findById(uuid)
                .filter(g -> !g.isDeleted())
                .orElseThrow(() -> new RuntimeException("GroupProperty not found via id"));
        return groupPropertyMapper.toDto(groupProperty);
    }

    public void delete(UUID uuid) {
        groupPropertyRepository.logicalRemove(uuid);
    }

}
