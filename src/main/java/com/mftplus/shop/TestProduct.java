package com.mftplus.shop;


import com.mftplus.shop.InventoryProduct.dto.InventoryProductDto;
import com.mftplus.shop.InventoryProduct.service.InventoryProductService;
import com.mftplus.shop.enums.InventoryStatus;
import com.mftplus.shop.groupProperty.GroupPropertyService;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.inventory.dto.InventoryDto;
import com.mftplus.shop.inventory.service.InventoryService;
import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import com.mftplus.shop.order.service.OrderService;
import com.mftplus.shop.order.service.PaymentService;
import com.mftplus.shop.orderItem.service.OrderItemService;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.service.PermissionService;
import com.mftplus.shop.product.ProductService;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.productGroup.ProductGroupService;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productPropertyValue.service.PropertyValueService;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.service.RoleService;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.repository.UserRepository;
import com.mftplus.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class TestProduct implements CommandLineRunner {
    private final ProductService productService;
    private final GroupPropertyService groupPropertyService;
    private final PropertyValueService propertyValueService;
    private final ProductGroupService productGroupService;
    private final InventoryService inventoryService;
    private final InventoryProductService inventoryProductService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;

    public TestProduct(ProductService productService, GroupPropertyService groupPropertyService, PropertyValueService propertyValueService, ProductGroupService productGroupService, InventoryService inventoryService, InventoryProductService inventoryProductService, PermissionService permissionService, RoleService roleService, UserService userService, UserRepository userRepository, OrderService orderService, OrderItemService orderItemService, PaymentService paymentService) {
        this.productService = productService;
        this.groupPropertyService = groupPropertyService;
        this.propertyValueService = propertyValueService;
        this.productGroupService = productGroupService;
        this.inventoryService = inventoryService;
        this.inventoryProductService = inventoryProductService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.paymentService = paymentService;
    }


    @Override
    public void run(String... args) throws Exception {

        PermissionDto permissionDto = PermissionDto.builder().permissionName("ACCESS-ALL").build();
        PermissionDto savedPermission = permissionService.save(permissionDto);

        RoleDto roleDto = RoleDto.builder().roleName("ROLE_ADMIN").permissionSet(Set.of(permissionDto)).build();
        RoleDto savedRole = roleService.save(roleDto);
//        System.out.println("RoleDto Saved : " + savedRole);





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
        System.out.println("Product Goup Saved :"+savedMobilePhones);
//        System.out.println("group and value :" + groupPropertyService.getGroupNameAndPropertyValue("128", "memory"));
////        System.out.println("product .group by id : "+productGroupService.findById(2L));

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
        ProductDto savedProduct = productService.save(productDto);
        System.out.println("Product saved :"+savedProduct);

//        productService.save(productDto);
//        System.out.println("Product group id found :"+productService.findByProductGroupId(savedMobilePhones.getId()));
//        System.out.println("Title Of PG : "+ productService.findByProductGroupTitle("Mobile Phones"));


        InventoryDto inventoryDto = InventoryDto.builder()
                .phone("0912323")
                .address("tehran-gharb")
                .title("digital")
                .quantity(12.0)
                .build();
        InventoryDto savedInventory = inventoryService.save(inventoryDto);

//        log.info("Inventory saved {}", inventoryService.save(inventoryDto));

        TransactionDto inventoryTransactionDto = TransactionDto.builder()
                .count(2.0)
                .status(InventoryStatus.outcome)
                .transactionDateTime(LocalDateTime.now())
                .build();

        InventoryProductDto inventoryProductDto = InventoryProductDto.builder()
                .productId(savedProduct.getId()) // باید حتماً ID (UUID) بدی
                .inventoryId(savedInventory.getId())
                .quantity(10.0)
                .title("Warehouse A")
                .phone("09394545")
                .transactions(List.of(inventoryTransactionDto)) // اضافه کردن تراکنش
                .build();

// ذخیره موجودیت InventoryProduct
        InventoryProductDto saved = inventoryProductService.save(inventoryProductDto);
//        System.out.println("InventoryProduct Saved: " + saved);


        UserDto userDto = UserDto.builder()
                .username("mobi")
                .password("m32145")
                .roleSet(Set.of(roleDto))
                .build();
        UserDto userSaved = userService.save(userDto); // حالا userSaved.getId() مقدار دارد

//        CheckDto checkDto = CheckDto.builder()
//                .checkNumber("123245Check")
//                .dateTime(LocalDateTime.now())
//                .amount(5990.0)
//                .build();
// احتمالاً نیاز است این را هم ذخیره کنی
//        CheckDto savedCheck = checkService.save(checkDto);

//        OrderItemDto orderItemDto = OrderItemDto.builder()
//                .productId(productDto.getId())
//                .build();
//        OrderItemDto savedItem = orderItemService.save(orderItemDto);

//        OrderDto orderDto = OrderDto.builder()
//                .userId(userSaved.getId()) // ✅ اصلاح‌شده
////                .orderItemIds(List.of(orderItemDto.getId()))
////                .paymentIds(List.of(checkDto.getId()))
//                .orderNumber("123344L")
//                .orderDate(LocalDate.now())
//                .amount(23.0)
//                .build();
//
//        OrderDto savedOrder = orderService.saveOrder(orderDto);
//        System.out.println("Order Saved : " + savedOrder);


    }

}
