package com.mftplus.shop.product;


import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductGroupRepository productGroupRepository;
    private final UuidMapper uuidMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductGroupRepository productGroupRepository, UuidMapper uuidMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productGroupRepository = productGroupRepository;
        this.uuidMapper = uuidMapper;
    }


    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto, "Product");
        if (productDto.getProductGroupId() != null) {
            Long groupId = uuidMapper.map(productDto.getProductGroupId(), "ProductGroup");
            ProductGroup productGroup = productGroupRepository.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found for id: "));
            product.setProductGroup(productGroup);
        } else {

            product.setProductGroup(null);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct, "Product");
    }

    @Transactional
    public ProductDto update(UUID uuid, ProductDto dto) {
        Long productId = uuidMapper.map(uuid, "Product");

        // یافتن محصول موجود
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // اعمال تغییرات از DTO به entity
        productMapper.updateFromDto(dto, product, "Product");

        // تنظیم ProductGroup اگر مقدار جدید داده شده
        if (dto.getProductGroupId() != null) {
            Long groupId = uuidMapper.map(dto.getProductGroupId(), "ProductGroup");
            ProductGroup productGroup = productGroupRepository.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException("ProductGroup not found"));
            product.setProductGroup(productGroup);
        } else {
            product.setProductGroup(null);
        }

        // ذخیره و بازگرداندن DTO
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved, "Product");
    }

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
        productGroupRepository.logicalRemove(productId);

    }

    //TODO debugging...
    @Transactional
    public ProductDto findByProductGroupId(UUID productGroupId) {
        Long groupId = uuidMapper.map(productGroupId, "ProductGroup");
        Product product = productRepository.findByProductGroupId(groupId).orElse(null);
        return productMapper.toDto(product, "Product");
    }

    @Transactional
    public ProductDto findByProductGroupTitle(String productGroupTitle) {
        Product result = productRepository.findByPGroupTitle(productGroupTitle).orElse(null);
        return productMapper.toDto(result, "Product");
    }


//    @Transactional
//    public List<ProductGroupDto> getAllActiveGroups() {
//        List<ProductGroup> groups = productGroupRepository.findByIsDeletedFalse();
//        return productGroupMapper.toDtoList(groups, "ProductGroup");
//    }


}
