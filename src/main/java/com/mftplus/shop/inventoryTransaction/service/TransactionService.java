package com.mftplus.shop.inventoryTransaction.service;

import com.mftplus.shop.InventoryProduct.entity.InventoryProduct;
import com.mftplus.shop.InventoryProduct.repository.InventoryProductRepository;
import com.mftplus.shop.inventoryTransaction.entity.InventoryTransaction;
import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import com.mftplus.shop.inventoryTransaction.mapper.InventoryTransactionMapper;
import com.mftplus.shop.inventoryTransaction.repository.TransactionRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final UuidMapper uuidMapper;
    private final TransactionRepository transactionRepository;
    private final InventoryTransactionMapper inventoryTransactionMapper;
    private final InventoryProductRepository inventoryProductRepository;

    public TransactionService(UuidMapper uuidMapper, TransactionRepository transactionRepository, InventoryTransactionMapper inventoryTransactionMapper, InventoryProductRepository inventoryProductRepository) {
        this.uuidMapper = uuidMapper;
        this.transactionRepository = transactionRepository;
        this.inventoryTransactionMapper = inventoryTransactionMapper;
        this.inventoryProductRepository = inventoryProductRepository;
    }

    @Transactional
    public TransactionDto save(TransactionDto dto) {

        if (dto.getInventoryProductId() == null) {
            throw new IllegalArgumentException("InventoryProductId is required");
        }

        Long inventoryProductId = uuidMapper.map(dto.getInventoryProductId(), "InventoryProduct");
        InventoryProduct inventoryProduct = inventoryProductRepository.findById(inventoryProductId)
                .orElseThrow(() -> new EntityNotFoundException("InventoryProduct not found"));

        InventoryTransaction transaction = inventoryTransactionMapper.toEntity(dto, "InventoryTransaction");
        transaction.setInventoryProduct(inventoryProduct);
        transaction.setDeleted(false);

        InventoryTransaction saved = transactionRepository.save(transaction);

        TransactionDto result = inventoryTransactionMapper.toDto(saved, "InventoryTransaction");
        result.setId(uuidMapper.map(saved.getId(), "InventoryTransaction"));
        return result;
    }

    @Transactional
    public TransactionDto update(UUID uuid, TransactionDto dto) {
        Long id = uuidMapper.map(uuid, "InventoryTransaction");

        InventoryTransaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InventoryTransaction not found"));

        inventoryTransactionMapper.updateFromDto(dto, existing, "InventoryTransaction");

        if (dto.getInventoryProductId() != null) {
            Long inventoryProductId = uuidMapper.map(dto.getInventoryProductId(), "InventoryProduct");
            InventoryProduct inventoryProduct = inventoryProductRepository.findById(inventoryProductId)
                    .orElseThrow(() -> new EntityNotFoundException("InventoryProduct not found"));
            existing.setInventoryProduct(inventoryProduct);
        }
        InventoryTransaction saved = transactionRepository.save(existing);
        TransactionDto resultDto = inventoryTransactionMapper.toDto(saved, "InventoryTransaction");
        resultDto.setId(uuidMapper.map(saved.getId(), "InventoryTransaction"));
        return resultDto;
    }

    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(i -> inventoryTransactionMapper.toDto(i, "InventoryTransaction"))
                .collect(Collectors.toList());
    }

    @Transactional
    public TransactionDto getByUuid(UUID uuid) {
        Long transactionId = uuidMapper.map(uuid, "InventoryTransaction");
        InventoryTransaction inventoryTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("InventoryTransaction not found for UUID"));

        return inventoryTransactionMapper.toDto(inventoryTransaction, "InventoryTransaction");

    }

    public void logicalRemove(UUID uuid) {
        Long transactionId = uuidMapper.map(uuid, "InventoryTransaction");
        transactionRepository.logicalRemove(transactionId);
    }

}
