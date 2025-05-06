package com.mftplus.shop.productPropertyValue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

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
    @GeneratedValue
    private UUID uuid;

    @Column(name = "propertyName")
    private String propertyName;

    @Column(name = "property_value")
    private String value;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "product_info")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "groupProperty_info")
    @JsonIgnore
    private GroupProperty groupProperty;

}
