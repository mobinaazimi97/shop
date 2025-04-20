package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.entity.Product;
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



    public Product toEntity() {
      return Product.builder()
              .id(productId)
              .name(name)
              .price(price)
              .code(productCode)
              .productGroup(productGroupDto != null ? productGroupDto.getProductGroup() : null)  // اگر parent وجود داشته باشد
//              .productGroup(productGroupDto.getProductGroup())
              .build();

    }


    public static ProductDto from(Product entity) {
        if (entity == null) return null;

        return ProductDto.builder()
//                .productId(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .productCode(entity.getCode())
                .productGroupDto(ProductGroupDto.from(entity.getProductGroup()))
                // تبدیل ProductGroup به ProductGroupDto
                .build();
    }

}
