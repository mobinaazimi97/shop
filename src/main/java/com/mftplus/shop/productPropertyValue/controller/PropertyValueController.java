package com.mftplus.shop.productPropertyValue.controller;

import com.mftplus.shop.productPropertyValue.service.PropertyValueService;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/values")
public class PropertyValueController {

    private final PropertyValueService propertyValueService;

    public PropertyValueController(PropertyValueService propertyValueService) {
        this.propertyValueService = propertyValueService;
    }

    @PostMapping
    public ResponseEntity<PropertyValueDto> createPropertyValue(@RequestBody PropertyValueDto propertyValueDto) {
        PropertyValueDto savedPropertyValueDto = propertyValueService.save(propertyValueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPropertyValueDto);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PropertyValueDto> updatePropertyValue(
            @PathVariable UUID uuid,
            @RequestBody PropertyValueDto propertyValueDto) {

        PropertyValueDto updated = propertyValueService.update(uuid, propertyValueDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteValueById(@PathVariable UUID uuid) {
        propertyValueService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<PropertyValueDto>> getAllPropertyValues() {
        List<PropertyValueDto> propertyValueDtos = propertyValueService.findAll();
        if (propertyValueDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(propertyValueDtos);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<PropertyValueDto> getValueById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(propertyValueService.getByUuid(uuid));
    }

}
