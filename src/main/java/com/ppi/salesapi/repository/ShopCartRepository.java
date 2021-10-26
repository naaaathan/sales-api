package com.ppi.salesapi.repository;

import com.ppi.salesapi.model.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {

    ShopCart findShopCartById(Long id);




}
