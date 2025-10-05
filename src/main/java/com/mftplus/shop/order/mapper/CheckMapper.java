package com.mftplus.shop.order.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.order.entity.Check;
import com.mftplus.shop.order.dto.CheckDto;
import com.mftplus.shop.user.mapper.UserMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, UserMapper.class})
public interface CheckMapper {

    @Mapping(target = "userId", source = "user.id")
    CheckDto toDto(Check entity, @Context String entityType);

    Check toEntity(CheckDto dto, @Context String entityType);

    void update(@MappingTarget Check entity, CheckDto dto, @Context String entityType);

    List<CheckDto> toDtoList(List<Check> checkList, @Context String entityType);

    List<Check> toEntityList(List<CheckDto> checkDtoList, @Context String entityType);

}
