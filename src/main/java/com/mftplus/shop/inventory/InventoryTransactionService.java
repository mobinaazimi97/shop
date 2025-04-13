package com.mftplus.shop.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryTransactionService {
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public InventoryTransactionService(InventoryTransactionRepository inventoryTransactionRepository) {
        this.inventoryTransactionRepository = inventoryTransactionRepository;
    }
    public InventoryTransaction save(InventoryTransaction inventoryTransaction) {
        return inventoryTransactionRepository.save(inventoryTransaction);
    }
    public InventoryTransaction update(InventoryTransaction inventoryTransaction) {
        return inventoryTransactionRepository.save(inventoryTransaction);
    }
    public InventoryTransaction findById(Long id) {
        return inventoryTransactionRepository.findById(id).orElse(null);
    }
    public List<InventoryTransaction> findAll() {
        return inventoryTransactionRepository.findAll();
    }
    public void delete(Long id) {
        inventoryTransactionRepository.deleteById(id);
    }
}
