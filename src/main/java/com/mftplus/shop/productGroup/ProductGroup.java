package com.mftplus.shop.productGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.productGroup.audit.listener.ChangeLoggerListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productGroupEntity")
@Table(name = "productGroup_tbl")
@Cacheable
@EntityListeners(ChangeLoggerListener.class)
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_title")
    private String title;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Column(name = "productGroup-child")
    private List<ProductGroup> childList = new ArrayList<>();

    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL) // شامل کردن فقط در صورت غیر null بودن
//    @JsonIgnoreProperties({"propertyValues"})
    @JsonProperty("parentId") // به صورت parent نمایش داده شود
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_parent_group"))
    private ProductGroup parent;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonProperty("groupProperty")
    @JoinColumn(name = "property_id", foreignKey = @ForeignKey(name = "fk_pGroup_group"))
    private GroupProperty groupProperty;


    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Version
    @Column(name = "VERSION")
    private Long version;
}
