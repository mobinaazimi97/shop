package com.mftplus.shop.productGroup;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    @Query("select p from productGroupEntity p where p.title = :title")
    Optional<ProductGroup> findByTitle(@Param("title") String title);

    @Query("""
                select p from productGroupEntity p
                join p.groupProperty g
                join g.propertyValues v
                where g.groupName = :groupName and v.value = :value
            """)
    List<ProductGroup> findByGroupNameAndPropertyValue(String groupName, String value);


    @Query("""
                select p from productGroupEntity p
                join p.groupProperty g
                join g.propertyValues v
                where g.groupName = :groupName and v.value = :value and p.title=:title
            """)
    List<ProductGroup> findByGroupNameAndPropertyValueAndTitle(String groupName, String value, String title);

    @Query("select p from productGroupEntity p where p.parent.id=:id")
    List<ProductGroup> findByParentId(Long id);

    @Query("select p from productGroupEntity p where p.parent.title=:title")
    List<ProductGroup> findByParentTitle(String title);

    @Modifying
    @Query("update productGroupEntity p set p.isDeleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);

}
