package com.mftplus.shop;


import com.mftplus.shop.groupProperty.GroupPropertyService;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.product.ProductService;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.productGroup.ProductGroupService;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productPropertyValue.PropertyValueService;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestProduct implements CommandLineRunner {
    private final ProductService productService;
    private final GroupPropertyService groupPropertyService;
    private final PropertyValueService propertyValueService;
    private final ProductGroupService productGroupService;

    public TestProduct(ProductService productService, GroupPropertyService groupPropertyService, PropertyValueService propertyValueService, ProductGroupService productGroupService) {
        this.productService = productService;
        this.groupPropertyService = groupPropertyService;
        this.propertyValueService = propertyValueService;
        this.productGroupService = productGroupService;
    }


    @Override
    public void run(String... args) throws Exception {

        ProductGroupDto electronicsDto = new ProductGroupDto();
        electronicsDto.setTitle("Electronics");
        ProductGroupDto savedElectronics = productGroupService.save(electronicsDto);

        ProductGroupDto mobilePhonesDto = new ProductGroupDto();
        mobilePhonesDto.setTitle("Mobile Phones");
        mobilePhonesDto.setParentId(savedElectronics.getId());
        ProductGroupDto savedMobilePhones = productGroupService.save(mobilePhonesDto);

        GroupPropertyDto groupPropertyDto = GroupPropertyDto.builder()
                .groupName("size") // فیلد 'name' به groupName در entity map میشه
                .productGroupId(savedMobilePhones.getId())
                .build();

        GroupPropertyDto savedGroupProperty = groupPropertyService.save(groupPropertyDto);

        System.out.println("groupPropertyDto saved: " + savedGroupProperty);


        PropertyValueDto propertyValueDto = PropertyValueDto.builder()
                .value("64")
                .groupPropertyId(savedGroupProperty.getId())
                .build();


        ProductDto productDto = ProductDto.builder()
                .price(23F)
                .productGroupId(savedMobilePhones.getId())
                .productName("laptop")
                .code("aasssas")
                .description("this as laptop lenovo")
                .propertyValues(List.of(propertyValueDto))
                .build();
        productService.save(productDto);

    }

}
