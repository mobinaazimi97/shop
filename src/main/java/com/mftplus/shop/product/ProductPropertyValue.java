package com.mftplus.shop.product;

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


@Entity(name = "productProEntity")
@Table(name = "product_properties_values")
public class ProductPropertyValue {
    @Id
    @SequenceGenerator(name = "productPropertiesGroupSeq", sequenceName = "product_properties_group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_properties_group_seq")
    private Long id;

    @Column(name = "property_value_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "value_info")
    private GroupProperty groupProperty;

}
