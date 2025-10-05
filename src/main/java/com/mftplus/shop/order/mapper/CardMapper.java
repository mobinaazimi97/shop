package com.mftplus.shop.order.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.order.entity.Card;
import com.mftplus.shop.order.dto.CardDto;
import com.mftplus.shop.user.mapper.UserMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, UserMapper.class})
public interface CardMapper {

    @Mapping(target = "userId", source = "user.id")
    CardDto toDto(Card entity, @Context String entityType);

    Card toEntity(CardDto dto, @Context String entityType);

    void update(@MappingTarget Card entity, CardDto dto, @Context String entityType);

    List<CardDto> toDtoList(List<Card> cardList, @Context String entityType);

    List<Card> toEntityList(List<CardDto> cardDtoList, @Context String entityType);
}
