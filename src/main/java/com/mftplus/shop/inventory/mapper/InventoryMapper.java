package com.mftplus.shop.inventory.mapper;

import com.mftplus.shop.InventoryProduct.mapper.InventoryProductMapper;
import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.inventory.Inventory;
import com.mftplus.shop.inventory.dto.InventoryDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, InventoryProductMapper.class})
public interface InventoryMapper {

    @Mapping(source = "inventoryProducts", target = "inventoryProducts")
    InventoryDto toDto(Inventory inventory, @Context String entityType);

    @Mapping(source = "inventoryProducts", target = "inventoryProducts")
    Inventory toEntity(InventoryDto inventoryDto, @Context String entityType);

    @Mapping(source = "inventoryProducts", target = "inventoryProducts")
    List<InventoryDto> toDtoList(List<Inventory> inventories, @Context String entityType);

    @Mapping(source = "inventoryProducts", target = "inventoryProducts")
    List<Inventory> toEntityList(List<InventoryDto> inventoryDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(InventoryDto inventoryDto, @MappingTarget Inventory inventory, @Context String entityType);
}
