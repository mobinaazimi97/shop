package com.mftplus.shop.productPropertyValue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString


@Entity(name = "valueEntity")
@Table(name = "property_values")
@Cacheable
public class PropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instance")
    private String value;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
