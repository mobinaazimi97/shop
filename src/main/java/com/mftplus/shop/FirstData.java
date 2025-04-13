package com.mftplus.shop;


import com.mftplus.shop.enums.InventoryStatus;
import com.mftplus.shop.inventory.InventoryProduct;
import com.mftplus.shop.inventory.InventoryProductService;
import com.mftplus.shop.inventory.InventoryTransaction;
import com.mftplus.shop.inventory.InventoryTransactionService;
import com.mftplus.shop.order.*;
import com.mftplus.shop.orderItem.OrderItem;
import com.mftplus.shop.permission.Permission;
import com.mftplus.shop.permission.PermissionService;
import com.mftplus.shop.product.*;
import com.mftplus.shop.role.Role;
import com.mftplus.shop.role.RoleService;
import com.mftplus.shop.user.User;
import com.mftplus.shop.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class FirstData implements CommandLineRunner {
    private final OrderServiceImpl orderServiceImpl;
    private final ProductService productService;
    private final GroupPropertyService groupPropertyService;
    private final ProductGroupService productGroupService;
    private final ProductPropertyValueService productPropertyValueService;
    private final InventoryProductService inventoryProductService;
    private final InventoryTransactionService inventoryTransactionService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final UserService userService;


    public FirstData(OrderServiceImpl orderServiceImpl,ProductService productService, GroupPropertyService groupPropertyService, ProductGroupService productGroupService, ProductPropertyValueService productPropertyValueService, InventoryProductService inventoryProductService, InventoryTransactionService inventoryTransactionService, PermissionService permissionService, RoleService roleService, UserService userService) {
        this.orderServiceImpl = orderServiceImpl;
        this.productService = productService;
        this.groupPropertyService = groupPropertyService;
        this.productGroupService = productGroupService;
        this.productPropertyValueService = productPropertyValueService;
        this.inventoryProductService = inventoryProductService;
        this.inventoryTransactionService = inventoryTransactionService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {


        Permission permission = Permission.builder().permissionName("BUY_PRODUCT").build();
        permissionService.save(permission);

        Role role = Role.builder().roleName("Customer").permissions(Set.of(permission)).build();
        roleService.save(role);

        User user = User.builder().username("alex").password("al123").roleSet(Set.of(role)).accountNonExpired(true).email("www.aaa.com").build();
        userService.save(user);

        Permission permission1 = Permission.builder().permissionName("BUY_PRODUCT_BY").build();
        permissionService.save(permission1);

        Role role1 = Role.builder().roleName("Customer").permissions(Set.of(permission1)).build();
        roleService.save(role1);

        User user1 = User.builder().username("sun").password("ss123").roleSet(Set.of(role1)).accountNonExpired(true).email("www.aaa.com").build();
        userService.save(user1);
//        -------------------------------------------------------------------------------------------------------------------------------------------
        // Product_Test
        ProductGroup child = new ProductGroup();
        ProductGroup parent = new ProductGroup();
        child.setName("digital");
        parent.setName("electronic");
        ProductPropertyValue productPropertyValue = ProductPropertyValue.builder().name("64G").build();
        GroupProperty groupProperty = GroupProperty.builder().name("ram").productPropertyValueList(List.of(productPropertyValue)).build();
        groupPropertyService.save(groupProperty);

        ProductGroup productGroup = ProductGroup.builder().groupProperty(groupProperty).name("laptop").parent(parent).childList(List.of(child)).build();

        Product product = Product.builder()
                .name("laptop")
                .price(20F)
                .productGroup(productGroup)
                .code(1L)
                .build();
        productService.save(product);
        System.out.println("Product :" + product.toString());
        Cash cash = Cash.builder().amount(2).dateTime(LocalDateTime.now()).user(user1).build();
        Card card = Card.builder().amount(3).user(user).build();
        Check check = Check.builder().checkNumber("232").amount(4).dateTime(LocalDateTime.now()).build();

        BigDecimal minAmount = new BigDecimal("100.00");
        Order order = Order.builder()
                .payments(List.of(cash, card))
                .orderNumber("123")
                .amount(2)
                .orderDate(LocalDate.of(2025, 1, 1))
                .build();
        orderServiceImpl.save(order);
        System.out.println("Order saved :" + order);

        OrderItem orderItem = OrderItem.builder()
                .amount(23)
                .price(45)
                .product(List.of(product))
                .quantity(2)
                .build();

        Order order1 = Order.builder()
                .payments(List.of(check))
                .orderNumber("323")
                .amount(5)
                .orderDate(LocalDate.of(2025, 2, 2))
                .orderItems(List.of(orderItem))
                .user(user1)
                .build();
        orderServiceImpl.save(order1);
        System.out.println("Order saved :" + order1);
        System.out.println(orderServiceImpl.findByPaymentId(2L));

        System.out.println("invoice amount less than 4 :" + orderServiceImpl.findOrdersByMaxAmount(4));

//        System.out.println("Date plus date"+orderServiceImpl.findOrdersAfterDate(LocalDate.of(2025,1,1)));
//        System.out.println("Amount Range Found :"+orderServiceImpl.findOrdersByAmountRange(1,6));
//        System.out.println("amount rang in date range :" + orderServiceImpl.findOrdersByAmountAndDateRange(1, 6, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2)));


        InventoryProduct inventoryProduct = InventoryProduct.builder()
                .products(List.of(product))
                .phone("091232234334")
                .title("tredmil")
                .quantity(23.0)
                .address("Tehran_west")
                .build();
        inventoryProductService.save(inventoryProduct);

        InventoryTransaction inventoryTransaction = InventoryTransaction.builder()
                .inventoryProducts(List.of(inventoryProduct))
                .count(1.0)
                .status(InventoryStatus.outcome)
                .orderItem(orderItem)
                .build();
        inventoryTransactionService.save(inventoryTransaction);
        System.out.println("inventoryTransaction" + inventoryTransaction);

    }
}
