package com.mftplus.shop.groupProperty;


import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/groups")
@Slf4j
public class GroupPropertyController {

    private final GroupPropertyService groupPropertyService;

    public GroupPropertyController(GroupPropertyService groupPropertyService) {
        this.groupPropertyService = groupPropertyService;
    }

    @PostMapping
    public ResponseEntity<GroupPropertyDto> createOrUpdateGroupProperty(@RequestBody GroupPropertyDto groupPropertyDto) {
        // فراخوانی متد save برای ذخیره GroupProperty همراه با PropertyValues
        GroupPropertyDto savedGroupProperty = groupPropertyService.save(groupPropertyDto);

        // بازگشت به عنوان ResponseEntity با وضعیت 201 (Created)
        return new ResponseEntity<>(savedGroupProperty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroupPropertyDto>> getAllGroupProperties() {
        List<GroupPropertyDto> groupProperties = groupPropertyService.findAll();

        // بررسی اینکه آیا داده‌ای پیدا شده یا خیر
        if (groupProperties.isEmpty()) {
            return ResponseEntity.noContent().build(); // اگر لیست خالی است، 204 No Content را باز می‌گرداند
        }

        return ResponseEntity.ok(groupProperties); // در غیر این صورت 200 OK با داده‌ها را باز می‌گرداند
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<GroupPropertyDto> updateGroupProperty(
            @PathVariable UUID uuid,
            @RequestBody GroupPropertyDto groupPropertyDto) {
        GroupPropertyDto update = groupPropertyService.update(uuid, groupPropertyDto);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/value/{value}/groupName/{groupName}")
    public ResponseEntity<List<GroupPropertyDto>> getGroupPropertyByGroupNameAndValue(@PathVariable String value, @PathVariable String groupName) {
        List<GroupPropertyDto> groupProperties = groupPropertyService.getGroupNameAndPropertyValue(value, groupName);
        return ResponseEntity.ok(groupProperties);
    }
//
//    @DeleteMapping("/{uuid}")
//    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
//        groupPropertyService.delete(uuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<GroupPropertyDto>> getGroupProperties() {
//        List<GroupPropertyDto> groupPropertyDtos = groupPropertyService.findAll();
//        return new ResponseEntity<>(groupPropertyDtos, HttpStatus.OK);
//    }
//
//    @GetMapping("/{uuid}")
//    public ResponseEntity<GroupPropertyDto> findById(@PathVariable UUID uuid) {
//        GroupPropertyDto groupPropertyDto = groupPropertyService.findById(uuid);
//        return ResponseEntity.ok(groupPropertyDto);
//    }

}
