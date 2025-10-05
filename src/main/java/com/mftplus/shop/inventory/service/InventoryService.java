package com.mftplus.shop.inventory.service;

import com.mftplus.shop.InventoryProduct.entity.InventoryProduct;
import com.mftplus.shop.InventoryProduct.repository.InventoryProductRepository;
import com.mftplus.shop.inventory.entity.Inventory;
import com.mftplus.shop.inventory.dto.InventoryDto;
import com.mftplus.shop.inventory.mapper.InventoryMapper;
import com.mftplus.shop.inventory.repository.InventoryRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryProductRepository inventoryProductRepository;
    private final InventoryMapper inventoryMapper;
    private final UuidMapper uuidMapper;

    public InventoryService(InventoryRepository inventoryRepository, InventoryProductRepository inventoryProductRepository, InventoryMapper inventoryMapper, UuidMapper uuidMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryProductRepository = inventoryProductRepository;
        this.inventoryMapper = inventoryMapper;
        this.uuidMapper = uuidMapper;
    }

    @Transactional
    public InventoryDto save(InventoryDto inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto, "Inventory");
        List<InventoryProduct> inventoryProducts = inventory.getInventoryProducts();
        //No auto persist
        inventory.setInventoryProducts(new ArrayList<>());

        Inventory savedInventory = inventoryRepository.save(inventory);

        if (inventoryProducts != null) {
            for (InventoryProduct product : inventoryProducts) {
                product.setInventory(savedInventory); // تنظیم رابطه دوطرفه
                inventoryProductRepository.save(product);
            }
            savedInventory.setInventoryProducts(
                    inventoryProductRepository.findByInventory(savedInventory)
            );
        }
        return inventoryMapper.toDto(savedInventory, "Inventory");
    }

    @Transactional
    public InventoryDto update(UUID uuid, InventoryDto dto) {
        Long id = uuidMapper.map(uuid, "Inventory");

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        inventoryMapper.updateFromDto(dto, inventory, "Inventory");

        Inventory updated = inventoryRepository.save(inventory);

        InventoryDto resultDto = inventoryMapper.toDto(updated, "Inventory");
        resultDto.setId(uuidMapper.map(updated.getId(), "Inventory"));

        return resultDto;
    }

    @Transactional
    public List<InventoryDto> findAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(i -> inventoryMapper.toDto(i, "Inventory"))
                .collect(Collectors.toList());
    }


    @Transactional
    public InventoryDto getUuid(UUID uuid) {
        Long inventoryId = uuidMapper.map(uuid, "Inventory");
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
        return inventoryMapper.toDto(inventory, "Inventory");
    }

    public void logicalRemove(UUID uuid) {
        Long inventoryId = uuidMapper.map(uuid, "Inventory");
        inventoryRepository.logicalRemove(inventoryId);
    }
}
