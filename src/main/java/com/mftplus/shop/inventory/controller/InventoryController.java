package com.mftplus.shop.inventory.controller;

import com.mftplus.shop.inventory.dto.InventoryDto;
import com.mftplus.shop.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryDto> save(@RequestBody InventoryDto inventoryDto) {
        InventoryDto saved = inventoryService.save(inventoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<InventoryDto> update(@PathVariable UUID uuid, @RequestBody InventoryDto inventoryDto) {
        InventoryDto updated = inventoryService.update(uuid, inventoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable UUID uuid) {
        inventoryService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAll() {
        List<InventoryDto> inventoryDtoList = inventoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(inventoryDtoList);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(inventoryService.getUuid(uuid));
    }


}
