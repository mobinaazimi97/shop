package com.mftplus.shop.groupProperty;

import com.mftplus.shop.productGroup.ProductGroup;
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
@Entity(name = "groupProEntity")
@Table(name = "group_property_tbl")
@Cacheable
public class GroupProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property",length = 100)
    private String groupName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "productGroup_id")
    private ProductGroup productGroup;
}
