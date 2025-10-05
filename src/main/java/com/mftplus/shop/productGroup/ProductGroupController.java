package com.mftplus.shop.productGroup;


import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/pGroups")
public class ProductGroupController {
    private final ProductGroupService productGroupService;

    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @PostMapping
    public ResponseEntity<ProductGroupDto> saveProductGroup(@RequestBody ProductGroupDto dto) {
        ProductGroupDto savedProductGroupDto = productGroupService.save(dto);
        return new ResponseEntity<>(savedProductGroupDto, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProductGroupDto> updateProductGroup(
            @PathVariable UUID uuid,
            @RequestBody ProductGroupDto dto) {

        ProductGroupDto updatedDto = productGroupService.updateByUuid(uuid, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteProductGroupById(@PathVariable UUID uuid) {
        productGroupService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getAllProductGroups() {
        List<ProductGroupDto> productGroups = productGroupService.findAll();
        return new ResponseEntity<>(productGroups, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ProductGroupDto> getProductGroup(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productGroupService.getByUuid(uuid));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ProductGroupDto>> getByTitle(@PathVariable String title) {
        List<ProductGroupDto> productGroupDtos = productGroupService.getByTitle(title);
        return ResponseEntity.ok(productGroupDtos);

    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<ProductGroupDto>> findByParentId(@PathVariable UUID parentId) {
        List<ProductGroupDto> result = productGroupService.findByParentId(parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/parentTitle/{parentTitle}")
    public ResponseEntity<List<ProductGroupDto>> getByParentTitle(@PathVariable String parentTitle) {
        List<ProductGroupDto> result = productGroupService.findByParentTitle(parentTitle);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/groupName/{groupName}/value/{value}")
    public ResponseEntity<List<ProductGroupDto>> getByGroupNameAndPropertyValue(
            @PathVariable String groupName,
            @PathVariable String value) {

        List<ProductGroupDto> result = productGroupService.getProductGroupsByGroupNameAndPropertyValue(groupName, value);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/groupName/{groupName}/value/{value}/title/{title}")
    public ResponseEntity<List<ProductGroupDto>> getByGroupNameAndPropertyValueAndTitle(
            @PathVariable String groupName,
            @PathVariable String value,
            @PathVariable String title) {

        List<ProductGroupDto> result = productGroupService.getProductGroupsByGroupNameAndPropertyValueAndTitle(groupName, value, title);
        return ResponseEntity.ok(result);
    }
}