package com.mftplus.shop.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryProductService {
    private final InventoryProductRepository inventoryProductRepository;

    public InventoryProductService(InventoryProductRepository inventoryProductRepository) {
        this.inventoryProductRepository = inventoryProductRepository;
    }

    public InventoryProduct save(InventoryProduct inventoryProduct) {
        return inventoryProductRepository.save(inventoryProduct);
    }

    public InventoryProduct update(InventoryProduct inventoryProduct) {
        return inventoryProductRepository.save(inventoryProduct);
    }

    public List<InventoryProduct> findAll() {
        return inventoryProductRepository.findAll();
    }

    public InventoryProduct findById(Long id) {
        return inventoryProductRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        inventoryProductRepository.deleteById(id);
    }
}
