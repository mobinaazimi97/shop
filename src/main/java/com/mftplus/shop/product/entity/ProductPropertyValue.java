package com.mftplus.shop.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString


@Entity(name = "productProEntity")
@Table(name = "product_properties_values")
@Cacheable
public class ProductPropertyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "param_value")
    private String value;

    @Column(name = "is_deleted")
    @SQLRestriction("deleted=false")
    private Boolean isDeleted = false;

}
