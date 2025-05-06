package com.mftplus.shop.productPropertyValue.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = CentralMapperConfig.class)
public interface PropertyValueMapper {

    @Mapping(source = "uuid", target = "id")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "product.uuid", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "groupProperty.uuid", target = "groupPropertyId")
    @Mapping(source = "groupProperty.groupName", target = "groupPropertyName")
    PropertyValueDto toDto(PropertyValue value);

    @Mapping(target = "groupProperty", ignore = true)
    @Mapping(target = "product", ignore = true)
    PropertyValue toEntity(PropertyValueDto dto);


//    @Mapping(target = "product", ignore = true)
//    @Mapping(source = "groupPropertyId", target = "groupProperty.uuid")
//    PropertyValue toEntity(PropertyValueDto dto);

    // @Mapping(source = "product.uuid", target = "productId") // تطبیق id محصول
    //    @Mapping(source = "groupProperty.id", target = "groupPropertyId") // تطبیق id گروه ویژگی
    //    PropertyValueDto toDto(PropertyValue entity);
    //
    //    @Mapping(source = "productId", target = "product.uuid") // تطبیق محصول
    //    @Mapping(source = "groupPropertyId", target = "groupProperty.id") // تطبیق گروه ویژگی
    //    PropertyValue toEntity(PropertyValueDto dto);

//    PropertyValueDto toDto(PropertyValue propertyValue);
//
//    PropertyValue getPropertyValueDto(PropertyValueDto propertyValueDto);
//
//    List<PropertyValueDto> toDtoAsList(List<PropertyValue> propertyValueList);

}
