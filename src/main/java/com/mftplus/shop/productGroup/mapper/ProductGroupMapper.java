package com.mftplus.shop.productGroup.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.groupProperty.GroupPropertyRepository;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.ProductGroupRepository;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, ProductGroupRepository.class, GroupPropertyRepository.class})
public interface ProductGroupMapper {
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "parentTitle", source = "parent.title")
    @Mapping(target = "groupPropertyDto", source = "productGroup.groupProperty")
    ProductGroupDto toDto(ProductGroup productGroup, @Context String entityType);

    ProductGroup toEntity(ProductGroupDto productGroupDto, @Context String entityType);

    List<ProductGroupDto> toDtoList(List<ProductGroup> productGroupList, @Context String entityType);

    List<ProductGroup> toEntityList(List<ProductGroupDto> productGroupDtoLis, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(ProductGroupDto productGroupDto, @MappingTarget ProductGroup productGroup, @Context String entityType);

}
