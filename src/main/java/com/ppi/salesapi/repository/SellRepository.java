package com.ppi.salesapi.repository;

import com.ppi.salesapi.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {

    Sell findSellById(Long id);

}
