package com.mftplus.shop.user.entity;

import com.mftplus.shop.role.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "userEntity")
@Table(name = "user_tbl")
@Cacheable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired = true;

    @Column(name = "credentials_expiry_date")
    private LocalDateTime credentialsExpiryDate;

    @Column(name = "enabled")
    private boolean enabled = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet = new HashSet<>();

    @SQLRestriction("deleted=false")
    private boolean deleted = false;
}
