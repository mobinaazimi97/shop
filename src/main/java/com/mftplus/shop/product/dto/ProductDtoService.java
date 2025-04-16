package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.ProductRepository;
import com.mftplus.shop.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDtoService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDtoService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto save(ProductDto dto) {
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);
        return productMapper.toDto(saved);
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Product updatedEntity = productMapper.toEntity(dto);
        updatedEntity.setId(existing.getId());

        Product updated = productRepository.save(updatedEntity);
        return productMapper.toDto(updated);
    }

    public List<ProductDto> findAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public void logicalRemove(Long id){
        productRepository.logicalRemove(id);
    }
//by dto
//    public ProductDto saveProductDto(ProductDto productDto) {
//        Product product = productDto.getProduct(); // Convert DTO to Entity
//        Product savedProduct = productRepository.save(product); // Save to DB
//        return productDto; // Convert saved entity to DTO
//    }

}
