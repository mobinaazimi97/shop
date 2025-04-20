package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.entity.Product;
import com.mftplus.shop.product.dto.ProductDto;


import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {

}
