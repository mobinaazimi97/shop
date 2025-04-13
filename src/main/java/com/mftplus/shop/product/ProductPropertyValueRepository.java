package com.mftplus.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValue, Long> {
}
