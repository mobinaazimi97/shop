package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.Product;
import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductDto {

    private UUID id;
    private String name;
    private Float price;
    private String description;
    private UUID productGroupId;
    private List<PropertyValueDto> propertyValues;


    public Product toEntity(ProductGroup productGroup) {
        Product product = new Product();
        product.setUuid(this.id != null ? this.id : UUID.randomUUID()); // در صورتی که UUID وجود نداشته باشد، آن را تولید می‌کنیم
        product.setProductName(this.name);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setProductGroup(productGroup); // گروه محصولی که دریافت شده
        return product;
    }

}
