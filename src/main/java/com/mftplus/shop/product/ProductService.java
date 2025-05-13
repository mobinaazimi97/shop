package com.mftplus.shop.product;


import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productGroup.mapper.ProductGroupMapper;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import com.mftplus.shop.productPropertyValue.PropertyValueRepository;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductGroupRepository productGroupRepository;
    private final PropertyValueRepository propertyValueRepository;
    private final GroupPropertyRepository groupPropertyRepository;
    private final PropertyValueMapper propertyValueMapper;
    private final UuidMapper uuidMapper;
    private final ProductGroupMapper productGroupMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductGroupRepository productGroupRepository, PropertyValueRepository propertyValueRepository, GroupPropertyRepository groupPropertyRepository, PropertyValueMapper propertyValueMapper, UuidMapper uuidMapper, ProductGroupMapper productGroupMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productGroupRepository = productGroupRepository;
        this.propertyValueRepository = propertyValueRepository;
        this.groupPropertyRepository = groupPropertyRepository;
        this.propertyValueMapper = propertyValueMapper;
        this.uuidMapper = uuidMapper;
        this.productGroupMapper = productGroupMapper;
    }

//    @Transactional
//    public ProductDto save(ProductDto productDto) {
//        // تبدیل ProductDto به Product entity
//        Product product = productMapper.toEntity(productDto, "Product");
//
//        // تنظیم ProductGroup از طریق UUID
//        if (productDto.getProductGroupId() != null) {
//            Long groupId = uuidMapper.map(productDto.getProductGroupId(), "ProductGroup");
//            ProductGroup productGroup = productGroupRepository.findById(groupId)
//                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));
//            product.setProductGroup(productGroup);
//        }
//
//        // تبدیل PropertyValueDto ها به PropertyValue entity و اضافه کردن آن‌ها به Product
//        if (productDto.getPropertyValues() != null && !productDto.getPropertyValues().isEmpty()) {
//            List<PropertyValue> propertyValues = productDto.getPropertyValues().stream().map(pvDto -> {
//                // تبدیل PropertyValueDto به PropertyValue
//                PropertyValue value = propertyValueMapper.toEntity(pvDto, "PropertyValue");
//
//                // پیدا کردن GroupProperty از طریق UUID
//                Long gpId = uuidMapper.map(pvDto.getGroupPropertyId(), "GroupProperty");
//                GroupProperty groupProperty = groupPropertyRepository.findById(gpId)
//                        .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));
//                value.setGroupProperty(groupProperty);
//
//                // اضافه کردن PropertyValue به Product
//                product.addPropertyValue(value);
//                return value;
//            }).collect(Collectors.toList());
//        }
//
//        // ذخیره کردن محصول و مرتبط کردن property ها
//        Product saved = productRepository.save(product);
//
//        // تبدیل entity به DTO و برگرداندن نتیجه
//        return productMapper.toDto(saved, "Product");
//    }

//    @Transactional
//    public ProductDto update(UUID productUuid, ProductDto productDto) {
//        // پیدا کردن موجودیت با UUID
//        Long productId = uuidMapper.map(productUuid, "Product");
//        Product existingProduct = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//
//        // آپدیت فیلدهای ساده با استفاده از مپپر
//        productMapper.updateFromDto(productDto, existingProduct, "Product");
//
//        // آپدیت ProductGroup اگر تغییر کرده باشد
//        if (productDto.getProductGroupId() != null) {
//            Long groupId = uuidMapper.map(productDto.getProductGroupId(), "ProductGroup");
//            ProductGroup productGroup = productGroupRepository.findById(groupId)
//                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));
//            existingProduct.setProductGroup(productGroup);
//        }
//
//        // پاک‌کردن PropertyValueهای قبلی
//        existingProduct.getPropertyValues().clear();
//
//        // اضافه کردن PropertyValueهای جدید
//        if (productDto.getPropertyValues() != null && !productDto.getPropertyValues().isEmpty()) {
//            for (PropertyValueDto pvDto : productDto.getPropertyValues()) {
//                PropertyValue value = propertyValueMapper.toEntity(pvDto, "PropertyValue");
//
//                Long gpId = uuidMapper.map(pvDto.getGroupPropertyId(), "GroupProperty");
//                GroupProperty groupProperty = groupPropertyRepository.findById(gpId)
//                        .orElseThrow(() -> new EntityNotFoundException("GroupProperty not found"));
//                value.setGroupProperty(groupProperty);
//
//                existingProduct.addPropertyValue(value);
//            }
//        }
//
//        // ذخیره و تبدیل به DTO
//        Product saved = productRepository.save(existingProduct);
//        return productMapper.toDto(saved, "Product");
//    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(p -> !p.isDeleted())
                .collect(Collectors.toList());

        return productMapper.toDtoList(products, "Product");
    }

    @Transactional
    public ProductDto getByUuid(UUID uuid) {
        Long productId = uuidMapper.map(uuid, "Product");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toDto(product, "Product");
    }


    public void logicalRemove(UUID uuid) {
        Long productId = uuidMapper.map(uuid, "Product");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);

    }

    public List<ProductDto> findByProductGroup(UUID productGroupUuid) {
        Long groupId = uuidMapper.map(productGroupUuid, "ProductGroup");
        ProductGroup productGroup = productGroupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));

        List<Product> products = productRepository.findByProductGroup(productGroup);
        return productMapper.toDtoList(products, "Product");
    }

//    @Transactional
//    public List<ProductGroupDto> getAllActiveGroups() {
//        List<ProductGroup> groups = productGroupRepository.findByIsDeletedFalse();
//        return productGroupMapper.toDtoList(groups, "ProductGroup");
//    }


}
