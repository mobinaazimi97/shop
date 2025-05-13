package com.mftplus.shop.productPropertyValue;

import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PropertyValueDto>> getAllPropertyValues() {
        List<PropertyValueDto> propertyValueDtos = propertyValueService.findAll();

        // بررسی اینکه آیا داده‌ای پیدا شده یا خیر
        if (propertyValueDtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // اگر لیست خالی است، 204 No Content را باز می‌گرداند
        }

        return ResponseEntity.ok(propertyValueDtos); // در غیر این صورت 200 OK با داده‌ها را باز می‌گرداند
    }
}
