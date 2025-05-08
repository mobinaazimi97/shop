package com.mftplus.shop.productGroup;


import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/pGroups")
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
    public ResponseEntity<ProductGroupDto> updateProductGroup(@RequestBody ProductGroupDto productGroupDto, @RequestBody Long id) {
        return ResponseEntity.ok(productGroupService.update(id, productGroupDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductGroupById(@PathVariable Long id) {
        productGroupService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getAllProductGroups() {
        List<ProductGroupDto> productGroups = productGroupService.findAll();
        return new ResponseEntity<>(productGroups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGroupDto> getProductGroupById(@PathVariable Long id) {
        ProductGroupDto productGroupDto = productGroupService.findById(id);
        return ResponseEntity.ok(productGroupDto);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productGroupService.getByUuid(uuid));
    }

}