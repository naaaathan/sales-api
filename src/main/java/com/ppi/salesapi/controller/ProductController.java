package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Product;
import com.ppi.salesapi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/id/{id}")
    public Product findProductById(@PathVariable(value = "id") Long id) {
        return productService.findProductById(id);
    }


    @GetMapping(value = "/name/{name}")
    private List<Product> findByName(@PathVariable("name") String name) {

        return productService.findProductsByName(name);

    }

    @PostMapping(value = "/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    private Long create(@RequestBody Product product) {

        return productService.createProduct(product);

    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void update(@PathVariable("id") Long id, @RequestBody Product product) {

        productService.updateProduct(id, product);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void delete(@PathVariable("id") Long id) {

        productService.delete(id);

    }


}
