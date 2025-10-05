package com.mftplus.shop.inventoryTransaction.mapper;

import com.mftplus.shop.InventoryProduct.mapper.InventoryProductMapper;
import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.inventoryTransaction.entity.InventoryTransaction;
import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, InventoryProductMapper.class})
public interface InventoryTransactionMapper {

    @Mapping(target = "inventoryProductId", source = "inventoryProduct.id")
    @Mapping(target = "inventoryProductName", source = "inventoryProduct.title")
    @Mapping(target = "inventoryProductPhone", source = "inventoryProduct.phone")
    @Mapping(target = "inventoryProductAddress", source = "inventoryProduct.address")
    TransactionDto toDto(InventoryTransaction transaction, @Context String entityType);

    @Mapping(target = "inventoryProduct.id", source = "inventoryProductId")
    @Mapping(target = "inventoryProduct.title", ignore = true)
    @Mapping(target = "inventoryProduct.phone", ignore = true)
    @Mapping(target = "inventoryProduct.address", ignore = true)
    InventoryTransaction toEntity(TransactionDto dto, @Context String entityType);

    List<TransactionDto> toDtoList(List<InventoryTransaction> transactions, @Context String entityType);

    List<InventoryTransaction> toEntityList(List<TransactionDto> dtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryProduct.id", source = "inventoryProductId")
    @Mapping(target = "inventoryProduct.title", ignore = true)
    @Mapping(target = "inventoryProduct.phone", ignore = true)
    @Mapping(target = "inventoryProduct.address", ignore = true)
    void updateFromDto(TransactionDto dto, @MappingTarget InventoryTransaction entity, @Context String entityType);
}
