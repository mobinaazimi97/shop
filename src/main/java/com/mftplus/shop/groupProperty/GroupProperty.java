package com.mftplus.shop.groupProperty;

import com.mftplus.shop.productGroup.ProductGroup;
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
@Entity(name = "groupProEntity")
@Table(name = "group_property_tbl")
@Cacheable
public class GroupProperty {
    @Id
    @GeneratedValue
    @Column(length = 40)
    private UUID uuid;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "group_proGroup")
    private ProductGroup productGroup;
}
