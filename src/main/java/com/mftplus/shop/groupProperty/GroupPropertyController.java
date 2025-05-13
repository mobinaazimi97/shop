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
        GroupPropertyDto savedGroupProperty = groupPropertyService.save(groupPropertyDto);
        return new ResponseEntity<>(savedGroupProperty, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<GroupPropertyDto> updateGroupProperty(
            @PathVariable UUID uuid,
            @RequestBody GroupPropertyDto groupPropertyDto) {
        GroupPropertyDto update = groupPropertyService.update(uuid, groupPropertyDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable UUID uuid) {
        groupPropertyService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<GroupPropertyDto>> getAllGroupProperties() {
        List<GroupPropertyDto> groupProperties = groupPropertyService.findAll();
        if (groupProperties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(groupProperties);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<GroupPropertyDto> getGroupId(@PathVariable UUID uuid) {
        return ResponseEntity.ok(groupPropertyService.getByUuid(uuid));
    }

    @GetMapping("/value/{value}/groupName/{groupName}")
    public ResponseEntity<List<GroupPropertyDto>> getGroupPropertyByGroupNameAndValue(@PathVariable String value, @PathVariable String groupName) {
        List<GroupPropertyDto> groupProperties = groupPropertyService.getGroupNameAndPropertyValue(value, groupName);
        return ResponseEntity.ok(groupProperties);
    }
}
