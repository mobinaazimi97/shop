package com.mftplus.shop.product;

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
public class ProductGroup{
    @Id
    @SequenceGenerator(name = "productGroupSeq", sequenceName = "productGroup_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productGroupSeq")
    private Long id;

    @Column(name = "product_group_name", nullable = false, length = 40)
    private String name;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Column(name = "productGroup-child")
    private List<ProductGroup> childList=new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ProductGroup parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_groupInfo")
    private GroupProperty groupProperty;




}


