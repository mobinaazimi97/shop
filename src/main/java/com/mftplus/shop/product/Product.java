package com.mftplus.shop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mftplus.shop.productGroup.ProductGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productEntity")
@Table(name = "product_tbl")
@Cacheable
@SQLRestriction("is_deleted=false")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 30, nullable = false, unique = true) //todo nullable...
    private String productName;

    @Column(name = "price", length = 30)
    @Min(value = 0, message = "Cant be negative")
    private Float price;

    @Column(name = "serialId")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productGroup_info")
    private ProductGroup productGroup;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "productInfos", unique = true)
    @JsonIgnore
    private String productInfos;

    @Column(name = "description")
    private String description;

//    public void addPropertyValue(PropertyValue propertyValue) {
//        propertyValues.add(propertyValue);
//        propertyValue.setProduct(this);
//    }
//
//    // متد برای حذف PropertyValue از Product
//    public void removePropertyValue(PropertyValue propertyValue) {
//        propertyValues.remove(propertyValue);
//        propertyValue.setProduct(null);
//    }

}
