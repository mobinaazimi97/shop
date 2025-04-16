package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductDto {

    private Long productId;
    private String name;
    private Float price;
    private Long productCode;
    private ProductGroupDto productGroupDto;


    public Product getProduct() {
      return Product.builder()
              .id(productId)
              .name(name)
              .price(price)
              .code(productCode)
              .productGroup(productGroupDto.getProductGroup())
              .build();

    }

}
