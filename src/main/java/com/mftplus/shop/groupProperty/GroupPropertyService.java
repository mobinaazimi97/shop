package com.mftplus.shop.groupProperty;


import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.groupProperty.mapper.GroupPropertyMapper;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GroupPropertyService {

    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;
    private final ProductGroupRepository productGroupRepository;
    private final UuidMapper uuidMapper;

    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper, ProductGroupRepository productGroupRepository, UuidMapper uuidMapper) {
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
        this.productGroupRepository = productGroupRepository;
        this.uuidMapper = uuidMapper;
    }

//    @Transactional
//    public GroupPropertyDto save(GroupPropertyDto groupPropertyDto) {
//        GroupProperty groupProperty = groupPropertyMapper.toEntity(groupPropertyDto, "GroupProperty");
//
//        if (groupPropertyDto.getProductGroupId() != null) {
//            Long productGroupId = uuidMapper.map(groupPropertyDto.getProductGroupId(), "ProductGroup");
//            ProductGroup productGroup = productGroupRepository.findById(productGroupId)
//                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));
//            groupProperty.setProductGroup(productGroup);
//        }
//
//        GroupProperty saved = groupPropertyRepository.save(groupProperty);
//        return groupPropertyMapper.toDto(saved, "GroupProperty");
//    }


//    public GroupPropertyDto update(UUID uuid, GroupPropertyDto dto) {
//        GroupProperty entity = groupPropertyRepository.findById(uuid)
//                .orElseThrow(() -> new RuntimeException("GroupProperty not found to update"));
//        entity.setGroupName(dto.getName());
//        if (dto.getProductGroupId() != null) {
//            ProductGroup productGroup = productGroupRepository.findById(dto.getProductGroupId())
//                    .orElseThrow(() -> new RuntimeException("ProductGroup not found in GroupProperty for update"));
//            entity.setProductGroup(productGroup);
//        }
//        GroupProperty updated = groupPropertyRepository.save(entity);
//        return groupPropertyMapper.toDto(updated);
//    }
//
//    public List<GroupPropertyDto> findAll() {
//        return groupPropertyRepository.findAll()
//                .stream()
//                .filter(g -> !g.isDeleted())
//                .map(groupPropertyMapper::toDto)
//                .toList();
//    }
//
//    public GroupPropertyDto findById(UUID uuid) {
//        GroupProperty groupProperty = groupPropertyRepository.findById(uuid)
//                .filter(g -> !g.isDeleted())
//                .orElseThrow(() -> new RuntimeException("GroupProperty not found via id"));
//        return groupPropertyMapper.toDto(groupProperty);
//    }
//
//    public void delete(UUID uuid) {
//        groupPropertyRepository.logicalRemove(uuid);
//    }

}
