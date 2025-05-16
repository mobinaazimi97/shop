package com.mftplus.shop.InventoryProduct.controller;

import com.mftplus.shop.InventoryProduct.dto.InventoryProductDto;
import com.mftplus.shop.InventoryProduct.service.InventoryProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inProduct")
public class InventoryProductController {
    private final InventoryProductService inventoryProductService;

    public InventoryProductController(InventoryProductService inventoryProductService) {
        this.inventoryProductService = inventoryProductService;
    }

    @PostMapping
    public ResponseEntity<InventoryProductDto> save(@RequestBody InventoryProductDto inventoryProductDto) {
        InventoryProductDto saved = inventoryProductService.save(inventoryProductDto);
        return ResponseEntity.ok().body(saved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<InventoryProductDto> update(
            @PathVariable UUID uuid,
            @RequestBody InventoryProductDto dto) {

        InventoryProductDto updatedDto = inventoryProductService.update(uuid, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID uuid) {
        inventoryProductService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<InventoryProductDto>> getAll() {
        List<InventoryProductDto> inventoryProductDtoList = inventoryProductService.findAll();
        return new ResponseEntity<>(inventoryProductDtoList, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<InventoryProductDto> getUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(inventoryProductService.getByUuid(uuid));
    }
}
