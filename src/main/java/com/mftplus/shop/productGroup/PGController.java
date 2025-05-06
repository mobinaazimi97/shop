package com.mftplus.shop.productGroup;


import com.mftplus.shop.exceptions.ResourceNotFoundException;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pGroups")
public class PGController {
    private final ProductGroupService productGroupService;

    public PGController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @PostMapping
    public ResponseEntity<ProductGroupDto> saveProductGroup(@RequestBody ProductGroupDto dto) {
        ProductGroupDto savedProductGroupDto = productGroupService.save(dto);
        return new ResponseEntity<>(savedProductGroupDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody ProductGroupDto productGroupDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(productGroupService.update(productGroupDto.getId(), productGroupDto));
    }

    @DeleteMapping("/{uuId}")
    public ResponseEntity<Void> deleteProductGroupById(@PathVariable UUID uuId) {
        productGroupService.delete(uuId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getAllProductGroups() {
        List<ProductGroupDto> productGroups = productGroupService.findAll();
        return new ResponseEntity<>(productGroups, HttpStatus.OK);
    }

    @GetMapping("/{uuId}")
    public ResponseEntity<ProductGroupDto> getProductGroupById(@PathVariable UUID uuId) {
        ProductGroupDto productGroupDto = productGroupService.findById(uuId);
        return ResponseEntity.ok(productGroupDto);
    }
}