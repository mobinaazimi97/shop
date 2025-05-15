package com.mftplus.shop;


import com.mftplus.shop.groupProperty.GroupPropertyService;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.product.ProductService;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.productGroup.ProductGroupService;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productPropertyValue.PropertyValueService;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
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

        PropertyValueDto propertyValueDto = PropertyValueDto.builder()
                .value("128")
                .build();

        GroupPropertyDto groupPropertyDto = GroupPropertyDto.builder()
                .groupName("memory")
                .propertyValues(List.of(propertyValueDto))
                .build();
//      groupPropertyService.save(groupPropertyDto);
//      log.info("saved group property {}",groupPropertyService.save(groupPropertyDto));


        ProductGroupDto electronicsDto = new ProductGroupDto();
        electronicsDto.setTitle("Electronics");
        ProductGroupDto savedElectronics = productGroupService.save(electronicsDto);

        ProductGroupDto mobilePhonesDto = new ProductGroupDto();
        mobilePhonesDto.setTitle("Mobile Phones");
        mobilePhonesDto.setParentId(savedElectronics.getId());
        mobilePhonesDto.setGroupPropertyDto(groupPropertyDto);
        mobilePhonesDto.setGroupPropertyDto(groupPropertyDto);
        ProductGroupDto savedMobilePhones = productGroupService.save(mobilePhonesDto);
        System.out.println(savedMobilePhones);
        System.out.println("group and value :"+groupPropertyService.getGroupNameAndPropertyValue("128","memory"));
////        System.out.println("product group by id : "+productGroupService.findById(2L));

//
//
        ProductDto productDto = ProductDto.builder()
                .price(23F)
                .productGroupId(savedMobilePhones.getId())
                .productName("laptop")
                .code("aasssas")
                .description("this as laptop lenovo")
                .productGroupId(savedMobilePhones.getId())
                .build();
        productService.save(productDto);
        System.out.println("Product group id found :"+productService.findByProductGroupId(savedMobilePhones.getId()));
        System.out.println("Title Of PG : "+ productService.findByProductGroupTitle("Mobile Phones"));
    }

}
