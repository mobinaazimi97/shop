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

        // childrenIds فقط در DTO هست و در این مرحله نیازی به update اونا نیست
        // اگر بخوای آپدیت children هم انجام بشه، باید جدا مدیریت بشه

        // ذخیره و بازگشت DTO به همراه UUID
        ProductGroup saved = productGroupRepository.save(entity);
        return productGroupMapper.toDto(saved, "ProductGroup");
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

    @Transactional
    public ProductGroupDto getByUuid(UUID uuid) {
        Long productGroupId = uuidMapper.map(uuid, "ProductGroup"); // تبدیل UUID به Long ID
        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found for UUID"));

        return productGroupMapper.toDto(productGroup, "ProductGroup"); // تبدیل entity به DTO با UUID
    }


    public void logicalRemove(UUID uuid) {
        Long productGroupId = uuidMapper.map(uuid, "ProductGroup");

        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Product group not found or already deleted: " + productGroupId));
        productGroup.setDeleted(true);
        productGroupRepository.save(productGroup);
    }
}
