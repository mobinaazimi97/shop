package com.mftplus.shop.groupProperty.mapper;


import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = CentralMapperConfig.class)
public interface GroupPropertyMapper {

    @Mapping(source = "uuid", target = "id")
    @Mapping(source = "groupName", target = "name")
    @Mapping(source = "productGroup.uuId", target = "productGroupId")
    @Mapping(source = "productGroup.title", target = "productGroupTitle")
        // اضافه شده
    GroupPropertyDto toDto(GroupProperty entity);

    @InheritInverseConfiguration
    @Mapping(target = "productGroup", ignore = true) // در Service ست می‌شود
    @Mapping(target = "isDeleted", ignore = true)
    GroupProperty toEntity(GroupPropertyDto dto);

}
