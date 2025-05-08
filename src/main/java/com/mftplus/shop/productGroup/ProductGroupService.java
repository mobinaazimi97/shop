package com.mftplus.shop.productGroup;


import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productGroup.mapper.ProductGroupMapper;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;
    private final UuidMapper uuidMapper;


    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper, UuidMapper uuidMapper) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
        this.uuidMapper = uuidMapper;
    }

    @Transactional
    public ProductGroupDto save(ProductGroupDto productGroupDto) {
        ProductGroup entity = productGroupMapper.toEntity(productGroupDto, "ProductGroup");

        // اگر parentId وجود دارد، باید parent را تنظیم کنیم
        if (productGroupDto.getParentId() != null) {
            Long parentId = uuidMapper.map(productGroupDto.getParentId(), "ProductGroup");
            ProductGroup parent = productGroupRepository.findById(parentId)
                    .orElseThrow(() -> new EntityNotFoundException("Parent group not found"));
            entity.setParent(parent);
        }

        // ذخیره‌سازی
        ProductGroup saved = productGroupRepository.save(entity);

        // تبدیل entity ذخیره‌شده به DTO
        return productGroupMapper.toDto(saved, "ProductGroup");
    }

    @Transactional
    public ProductGroupDto update(Long id, ProductGroupDto productGroupDto) {
        ProductGroup productGroup = productGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product group not found to update "));
        productGroupMapper.updateFromDto(productGroupDto, productGroup, "ProductGroup");
        return productGroupMapper.toDto(productGroupRepository.save(productGroup), "ProductGroup");
    }

    public List<ProductGroupDto> findAll() {
        return productGroupRepository.findAll()
                .stream()
                .map(p -> productGroupMapper.toDto(p, "ProductGroup"))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductGroupDto findById(Long id) {
        ProductGroup productGroup = productGroupRepository.findById(id).orElse(null);
        return productGroupMapper.toDto(productGroup, "ProductGroup");
    }

    //todo
    @Transactional
    public ProductGroupDto getByUuid(UUID uuid) {
        Long productGroupId = uuidMapper.map(uuid, "ProductGroup"); // تبدیل UUID به Long ID
        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found for UUID"));

        return productGroupMapper.toDto(productGroup, "ProductGroup"); // تبدیل entity به DTO با UUID
    }


    public void delete(Long id) {
        ProductGroup group = productGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found or already deleted: " + id));
        group.setDeleted(true);
        productGroupRepository.save(group);
    }
}
