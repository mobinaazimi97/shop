package com.mftplus.shop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
@Entity(name = "groupProEntity")
@Table(name = "group_property")
public class GroupProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name")
    private String name;

//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    private Product product;

//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    private ProductGroup productGroup; // Todo

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "value_info")
    private ProductPropertyValue productPropertyValue;

    @Column(name = "is_deleted")
    @SQLRestriction("deleted=false")
    private Boolean isDeleted = false;
}
