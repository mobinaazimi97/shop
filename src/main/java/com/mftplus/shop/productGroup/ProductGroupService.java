package com.mftplus.shop.productGroup;


import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productGroup.mapper.ProductGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Slf4j
@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;


    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
    }

    @Transactional
    public ProductGroupDto save(ProductGroupDto productGroupDto) {
        ProductGroup productGroup = productGroupMapper.toEntity(productGroupDto, "ProductGroup");
        ProductGroup savedProductGroup = productGroupRepository.save(productGroup);
        return productGroupMapper.toDto(savedProductGroup, "ProductGroup");

//        if (productGroupDto.getParentId() == null) {
//            ProductGroup productGroup = productGroupMapper.toEntity(productGroupDto, "ProductGroupParent");
//            ProductGroup savedProductGroup = productGroupRepository.save(productGroup);
//            return productGroupMapper.toDto(savedProductGroup, "ProductGroupParent");
//
//        }else {
//            ProductGroup productGroup = productGroupMapper.toEntity(productGroupDto, "ProductGroup");
//            ProductGroup savedProductGroup = productGroupRepository.save(productGroup);
//            return productGroupMapper.toDto(savedProductGroup, "ProductGroup");


        // Convert DTO to Entity

        // Save the entity
//        ProductGroup savedProductGroup = productGroupRepository.save(productGroup);
//         Convert back to DTO and return
//        return productGroupMapper.toDto(savedProductGroup, "ProductGroup");
    }

//    public ProductGroupDto findById(UUID id) {
//
//    }


//    public ProductGroupDto update(UUID uuId, ProductGroupDto productGroupDto) throws ResourceNotFoundException {
//        ProductGroup productGroup = productGroupRepository.findById(uuId)
//                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + uuId));
//
//        productGroup.setTitle(productGroupDto.getTitle());
//
//        if (productGroupDto.getParentId() != null) {
//            ProductGroup parent = productGroupRepository.findById(productGroupDto.getParentId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Parent group not found with id: " + productGroupDto.getParentId()));
//            productGroup.setParent(parent);
//        } else {
//            productGroup.setParent(null);
//        }
//        ProductGroup updatedProductGroup = productGroupRepository.save(productGroup);
//        return productGroupMapper.toDto(updatedProductGroup);
//    }
//
//    public List<ProductGroupDto> findAll() {
//        return productGroupRepository.findAll().stream()
//                .filter(g -> !g.isDeleted())
//                .map(productGroupMapper::toDto)
//                .toList();
//    }
//
//    public ProductGroupDto findById(UUID uuId) {
//        ProductGroup productGroup = productGroupRepository.findById(uuId)
//                .filter(g -> !g.isDeleted())
//                .orElseThrow(() -> new RuntimeException("Group not found"));
//        return productGroupMapper.toDto(productGroup);
//    }
//
//    public void delete(UUID uuId) {
//        ProductGroup group = productGroupRepository.findByUuIdAndIsDeletedFalse(uuId)
//                .orElseThrow(() -> new EntityNotFoundException("Product group not found or already deleted: " + uuId));
//
//        group.setDeleted(true);
//        productGroupRepository.save(group);
//    }


}
