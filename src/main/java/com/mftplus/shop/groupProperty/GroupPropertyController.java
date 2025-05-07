package com.mftplus.shop.groupProperty;


import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/groups")
@Slf4j
public class GroupPropertyController {

    private final GroupPropertyService groupPropertyService;

    public GroupPropertyController(GroupPropertyService groupPropertyService) {
        this.groupPropertyService = groupPropertyService;
    }

//    @PostMapping
//    public ResponseEntity<GroupPropertyDto> saveGroupProperty(@RequestBody GroupPropertyDto groupPropertyDto) {
//        GroupPropertyDto savedGroupPropertyDto = groupPropertyService.save(groupPropertyDto);
//        return new ResponseEntity<>(savedGroupPropertyDto, HttpStatus.CREATED);
//    }
//
//    @PutMapping
//    public ResponseEntity<GroupPropertyDto> updateGroupProperty(@RequestBody GroupPropertyDto groupPropertyDto) {
//        return ResponseEntity.ok(groupPropertyService.update(groupPropertyDto.getId(), groupPropertyDto));
//    }
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
