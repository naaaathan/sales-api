package com.ppi.salesapi.repository;

import com.ppi.salesapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List<Product> findProductsByName(String name);

    List<Product> findAllByOrderByPrice();
}
