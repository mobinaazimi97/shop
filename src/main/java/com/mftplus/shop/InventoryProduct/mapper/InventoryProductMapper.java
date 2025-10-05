package com.mftplus.shop.InventoryProduct.mapper;

import com.mftplus.shop.InventoryProduct.entity.InventoryProduct;
import com.mftplus.shop.InventoryProduct.dto.InventoryProductDto;
import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.inventory.mapper.InventoryMapper;
import com.mftplus.shop.inventoryTransaction.mapper.InventoryTransactionMapper;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, InventoryMapper.class, ProductMapper.class, InventoryTransactionMapper.class})
public interface InventoryProductMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "inventory.title", target = "inventoryName")
    @Mapping(source = "inventory.phone", target = "inventoryPhone")
    @Mapping(source = "inventory.address", target = "inventoryAddress")
    @Mapping(source = "inventory.quantity", target = "inventoryQuantity")
    @Mapping(source = "transactions", target = "transactions")
    InventoryProductDto toDto(InventoryProduct inventoryProduct, @Context String entityType);

    @Mapping(target = "product", ignore = true) // چون دستی در Service ست می‌کنی
    @Mapping(target = "inventory", ignore = true)
    @Mapping(target = "transactions", ignore = true)
        // چون باید برای این‌ها دستی د
    InventoryProduct toEntity(InventoryProductDto inventoryProductDto, @Context String entityType);

    List<InventoryProductDto> toDtoList(List<InventoryProduct> inventoryProductList, @Context String entityType);

    List<InventoryProduct> toEntityList(List<InventoryProductDto> inventoryProductDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(InventoryProductDto inventoryProductDto, @MappingTarget InventoryProduct inventoryProduct, @Context String entityType);

}
