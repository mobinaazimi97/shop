package com.mftplus.shop.inventoryTransaction.controller;

import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import com.mftplus.shop.inventoryTransaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inTransaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto dto) {
        TransactionDto saved = transactionService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TransactionDto> update(
            @PathVariable UUID uuid,
            @RequestBody TransactionDto dto) {

        TransactionDto updatedDto = transactionService.update(uuid, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID uuid) {
        transactionService.logicalRemove(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllProductGroups() {
        List<TransactionDto> transactionDtoList = transactionService.findAll();
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<TransactionDto> getUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(transactionService.getByUuid(uuid));
    }
}
