package com.mftplus.shop.product.entity;

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
@Table(name = "product_tbl" , indexes = {
        @Index(name = "idx_product_productGroup_productGroup_name",columnList = "name")
})
@Cacheable
public class Product {
    @Id
    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    private Long id;

    @Column(name = "product_name", length = 30) //todo nullable...
//  @Pattern(regexp = "^[a-zA-Z]{3,30}$",message = "invalid name!")
    private String name;

    @Column(name = "price", length = 30)
//    @Pattern(regexp = "^[0-9]{2,30}$",message = "invalid price")
    @Min(value = 0, message = "Cant be negative")
    private Float price;

    @Column(name = "product_code")
    private Long code;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "p_group")
    private ProductGroup productGroup;

    @Column(name = "is_deleted")
    @SQLRestriction("deleted=false")
    private Boolean isDeleted = false;

}
