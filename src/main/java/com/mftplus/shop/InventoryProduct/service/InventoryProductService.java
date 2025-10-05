package com.mftplus.shop.InventoryProduct.service;

import com.mftplus.shop.InventoryProduct.entity.InventoryProduct;
import com.mftplus.shop.InventoryProduct.dto.InventoryProductDto;
import com.mftplus.shop.InventoryProduct.mapper.InventoryProductMapper;
import com.mftplus.shop.InventoryProduct.repository.InventoryProductRepository;
import com.mftplus.shop.inventory.entity.Inventory;
import com.mftplus.shop.inventory.repository.InventoryRepository;
import com.mftplus.shop.inventoryTransaction.entity.InventoryTransaction;
import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import com.mftplus.shop.inventoryTransaction.mapper.InventoryTransactionMapper;
import com.mftplus.shop.inventoryTransaction.repository.TransactionRepository;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.ProductRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryProductService {
    private final InventoryProductRepository inventoryProductRepository;
    private final InventoryProductMapper inventoryProductMapper;
    private final InventoryTransactionMapper inventoryTransactionMapper;
    private final UuidMapper uuidMapper;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public InventoryProductService(InventoryProductRepository inventoryProductRepository, InventoryProductMapper inventoryProductMapper, InventoryTransactionMapper inventoryTransactionMapper, UuidMapper uuidMapper, InventoryRepository inventoryRepository, ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.inventoryProductRepository = inventoryProductRepository;
        this.inventoryProductMapper = inventoryProductMapper;
        this.inventoryTransactionMapper = inventoryTransactionMapper;
        this.uuidMapper = uuidMapper;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public InventoryProductDto save(InventoryProductDto dto) {
        InventoryProduct inventoryProduct = inventoryProductMapper.toEntity(dto, "InventoryProduct");
        if (dto.getProductId() != null) {
            Long productId = uuidMapper.map(dto.getProductId(), "Product");
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            inventoryProduct.setProduct(product);
        }
        if (dto.getInventoryId() != null) {
            Long inventoryId = uuidMapper.map(dto.getInventoryId(), "Inventory");
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
            inventoryProduct.setInventory(inventory);
        }

        if (dto.getTransactions() != null && !dto.getTransactions().isEmpty()) {
            List<InventoryTransaction> transactionEntities = new ArrayList<>();

            for (TransactionDto transactionDto : dto.getTransactions()) {
                InventoryTransaction transaction;

                if (transactionDto.getId() != null) {
                    Long transactionId = uuidMapper.map(transactionDto.getId(), "Transaction");
                    transaction = transactionRepository.findById(transactionId)
                            .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
                } else {
                    transaction = new InventoryTransaction();
                    transaction.setCount(transactionDto.getCount());
                    transaction.setStatus(transactionDto.getStatus());
                    transaction.setTransactionDateTime(transactionDto.getTransactionDateTime());
                }

                transaction.setInventoryProduct(inventoryProduct);
                transactionEntities.add(transaction);
            }

            inventoryProduct.setTransactions(transactionEntities);
        }

        InventoryProduct savedEntity = inventoryProductRepository.save(inventoryProduct);

        InventoryProductDto resultDto = inventoryProductMapper.toDto(savedEntity, "InventoryProduct");
        resultDto.setId(uuidMapper.map(savedEntity.getId(), "InventoryProduct"));
        return resultDto;
    }

    @Transactional
    public InventoryProductDto update(UUID uuid, InventoryProductDto dto) {
        Long id = uuidMapper.map(uuid, "InventoryProduct");

        InventoryProduct existing = inventoryProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InventoryProduct not found"));

        inventoryProductMapper.updateFromDto(dto, existing, "InventoryProduct");

        if (dto.getProductId() != null) {
            Product product = productRepository.findById(uuidMapper.map(dto.getProductId(), "Product"))
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            existing.setProduct(product);
        }

        if (dto.getInventoryId() != null) {
            Inventory inventory = inventoryRepository.findById(uuidMapper.map(dto.getInventoryId(), "Inventory"))
                    .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
            existing.setInventory(inventory);
        }

        if (dto.getTransactions() != null) {
            existing.getTransactions().clear(); // orphanRemoval

            List<InventoryTransaction> updatedTransactions = dto.getTransactions().stream()
                    .map(txDto -> {
                        InventoryTransaction tx = inventoryTransactionMapper.toEntity(txDto, "InventoryTransaction");
                        tx.setInventoryProduct(existing);
                        return tx;
                    }).collect(Collectors.toList());

            existing.getTransactions().addAll(updatedTransactions);
        }

        InventoryProduct saved = inventoryProductRepository.save(existing);

        return inventoryProductMapper.toDto(saved, "InventoryProduct");
    }

    public void logicalRemove(UUID uuid) {
        Long inProductId = uuidMapper.map(uuid, "InventoryProduct");
        inventoryProductRepository.logicalRemove(inProductId);
    }

    public List<InventoryProductDto> findAll() {
        return inventoryProductRepository.findAll()
                .stream()
                .map(p -> inventoryProductMapper.toDto(p, "InventoryProduct"))
                .collect(Collectors.toList());
    }

    @Transactional
    public InventoryProductDto getByUuid(UUID uuid) {
        Long inProductId = uuidMapper.map(uuid, "InventoryProduct");
        InventoryProduct inventoryProduct = inventoryProductRepository.findById(inProductId)
                .orElseThrow(() -> new EntityNotFoundException("InventoryProduct group not found for UUID"));

        return inventoryProductMapper.toDto(inventoryProduct, "InventoryProduct");
    }

}
