package com.mftplus.shop;


import com.mftplus.shop.order.OrderServiceImpl;
import com.mftplus.shop.order.service.PaymentService;
import com.mftplus.shop.permission.service.PermissionService;
import com.mftplus.shop.role.service.RoleService;
import com.mftplus.shop.user.repository.UserRepository;
import com.mftplus.shop.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UserFirstTest implements CommandLineRunner {
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderServiceImpl orderService;
    private final PaymentService paymentService;

    public UserFirstTest(PermissionService permissionService, RoleService roleService, UserService userService, UserRepository userRepository, OrderServiceImpl orderService, PaymentService paymentService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }


    @Override
    public void run(String... args) {
        System.out.println("----------------------------------------------------------------------------");
//        Permission permission1 = Permission.builder().permissionName("BUY_PRODUCT").build();
//        PermissionDto permissionDto1 = permissionService.save(permissionMapper.toDto(permission1, "Permission"));
//
//        Permission permission2 = Permission.builder().permissionName("SELL_PRODUCT").build();
//        PermissionDto permissionDto2 = permissionService.save(permissionMapper.toDto(permission2, "Permission"));
//
//        Permission permission3 = Permission.builder().permissionName("EDIT_PRODUCT").build();
//        PermissionDto permissionDto3 = permissionService.save(permissionMapper.toDto(permission3, "Permission"));
//
//        System.out.println("permissions : " + permissionService.findAll());
//        System.out.println("------------------------------------------------");
//        PermissionDto permissionDto = PermissionDto.builder().permissionName("ACCESS-ALL").build();
//        PermissionDto savedPermission = permissionService.save(permissionDto);

//        Role role1 = Role.builder().roleName("ROLE_CUSTOMER").permissionSet(Set.of(permission1, permission2)).build();
//        System.out.println(role1);
//        System.out.println(roleMapper.toDto(role1, "Role"));
//        roleService.save(roleMapper.toDto(role1, "Role"));
//
//        Role role2 = Role.builder().roleName("ROLE_USER").permissionSet(Set.of(permission2, permission3)).build();
//        roleService.save(roleMapper.toDto(role2, "Role"));

//        RoleDto roleDto = RoleDto.builder().roleName("ADMIN").permissionSet(Set.of(permissionDto)).build();
//        RoleDto saved = roleService.save(roleDto);
//        System.out.println("RoleDto Saved : " + saved);

//        System.out.println("roles : " + roleService.findAll());
//        System.out.println("------------------------------------------------");

//        UserDto userDto = UserDto.builder()
//                .username("mobi")
//                .password("m32145")
//                .roleSet(Set.of(roleDto))
//                .build();
//        UserDto userSaved = userService.save(userDto);
//        System.out.println("UserDto Saved : " + userSaved);

//        User user4 = User.builder().username("alex").password("al123").roleSet(Set.of(role1)).accountNonExpired(true).email("www.aaa.com").build();
//        User user1 = User.builder().username("sun").password("ss123").roleSet(Set.of(role1)).accountNonExpired(true).email("www.aaa.com").build();
//        User user2 = User.builder().username("mahdiar").password("mm123").roleSet(Set.of(role2)).accountNonExpired(true).email("www.mahdiar.com").build();
//        User user3 = User.builder().username("mobina").password("m456").roleSet(Set.of(role2)).accountNonExpired(true).email("www.mobina.com").build();
//        UserDto userDto1 = userMapper.toDto(user1, "User");
//        UserDto userDto2 = userMapper.toDto(user2, "User");
//        UserDto userDto3 = userMapper.toDto(user3, "User");
//        UserDto userDto4 = userMapper.toDto(user4, "User");
//        userService.saveAll(List.of(userDto1, userDto2, userDto3, userDto4));
//
//        System.out.println("users : " + userService.findAll());
//        System.out.println("---------------------------------------------------");
//
//        System.out.println("user find by id = 2 :" + userService.findById(2L));
//        System.out.println("user find by username = mahdiar :" + userService.findByUsername("mahdiar"));


//        User user = User.builder().username("sarah").password("sh435").build();
//        userRepository.save(user);
//
//        Check check = Check.builder().checkNumber("123245Check").user(user).dateTime(LocalDateTime.now()).amount(5990.0).build();
//
//        OrderItem orderItem = OrderItem.builder().amount(6.0).price(566.0).quantity(6).build();
//
//        Order order = Order.builder()
//                .user(user)
//                .orderItems(List.of(orderItem))
//                .orderNumber("123344L")
//                .orderDate(LocalDate.now())
//                .amount(23.0)
//                .payments(List.of(check))
//                .build();
//        Order savedOrder = orderService.save(order);
//        System.out.println("Order Saved : " + savedOrder);
//
//        System.out.println("-----------------------------------------------------------------");
//
//        PaymentDto paymentDto = PaymentDto.builder().amount(23.3).build();
//        PaymentDto savedPay = paymentService.savePayment(paymentDto);
//        System.out.println("Payment Saved : " + savedPay);
//        System.out.println("All Pays :"+paymentService.getAllPayments());
////        System.out.println(paymentService.findById());


    }
}
