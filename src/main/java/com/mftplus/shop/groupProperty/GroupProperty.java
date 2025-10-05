package com.mftplus.shop.groupProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mftplus.shop.productPropertyValue.entity.PropertyValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
@Entity(name = "groupProEntity")
@Table(name = "group_property_tbl")
@Cacheable
public class GroupProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property", length = 100)
    private String groupName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "property_instance",foreignKey = @ForeignKey(name = "fk_instanceProperty"))
    @JsonInclude(JsonInclude.Include.NON_NULL) // شامل کردن فقط در صورت غیر null بودن
    @JsonIgnore
    private List<PropertyValue> propertyValues = new ArrayList<>();

}
