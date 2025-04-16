package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.GroupProperty;
import com.mftplus.shop.product.GroupPropertyRepository;
import com.mftplus.shop.product.mapper.GroupPropertyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPropertyServiceDto {
    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;

    public GroupPropertyServiceDto(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper) {
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
    }

    public GroupPropertyDto save(GroupPropertyDto dto) {
        GroupProperty entity = groupPropertyMapper.toEntity(dto);
        GroupProperty saved = groupPropertyRepository.save(entity);
        return groupPropertyMapper.toDto(saved);
    }

    public GroupPropertyDto update(Long id, GroupPropertyDto dto) {
        GroupProperty existing = groupPropertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupProperty not found"));
        GroupProperty updatedEntity = groupPropertyMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());
        GroupProperty updated = groupPropertyRepository.save(updatedEntity);
        return groupPropertyMapper.toDto(updated);
    }

    public List<GroupPropertyDto> findAll() {
        return groupPropertyMapper.toDtoList(groupPropertyRepository.findAll());
    }

    public GroupPropertyDto findById(Long id) {
        return groupPropertyRepository.findById(id)
                .map(groupPropertyMapper::toDto)
                .orElseThrow(() -> new RuntimeException("GroupProperty not found"));
    }

    public void logicalRemove(Long id) {
        groupPropertyRepository.logicalRemove(id);
    }

}
