package com.ppi.salesapi.service;

import com.ppi.salesapi.model.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long id);

    List<Product> findProductsByName(String name);

    List<Product> findAll();

    Long createProduct(Product product);

    void updateProduct(Long id, Product product);

    void delete(Long id);
}
