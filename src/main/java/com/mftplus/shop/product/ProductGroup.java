package com.mftplus.shop.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

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
public class ProductGroup {
    @Id
    @SequenceGenerator(name = "productGroupSeq", sequenceName = "productGroup_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productGroupSeq")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
//    @Pattern(regexp = "^[a-zA-Z]{3,30}$", message = "invalid name!")
    private String name;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Column(name = "productGroup-child")
    @JsonManagedReference
    private List<ProductGroup> childList=new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonBackReference
    private ProductGroup parent;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    @JsonIgnore
    private GroupProperty groupProperty;

    @Column(name = "is_deleted")
    @SQLRestriction("deleted=false")
    private Boolean isDeleted = false;


}


