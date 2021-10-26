package com.ppi.salesapi.service.impl;

import com.ppi.salesapi.model.Product;
import com.ppi.salesapi.repository.ProductRepository;
import com.ppi.salesapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findProductsByName(String name) {

        Assert.notNull(name, "Name cannot be null");

        return productRepository.findProductsByName(name);

    }

    public Product findProductById(Long id) {

        return productRepository.findProductById(id);


    }

    public List<Product> findAll() {

        return productRepository.findAll();

    }

    public void createProduct(Product product) {

        Assert.notNull(product, "Product cannot be null");

        fillProductBasedOnAnotherAndSave(new Product(), product);
    }

    public void updateProduct(Long id, Product product) {

        Assert.notNull(product, "Product cannot be null");

        Product productToUpdate = productRepository.findProductById(id);

        if (ObjectUtils.isEmpty(productToUpdate)) {
            throw new RuntimeException("Didn't found product to be updated");
        }

        fillProductBasedOnAnotherAndSave(productToUpdate, product);

    }

    public void delete(Long id) {

        productRepository.deleteById(id);

    }

    private void fillProductBasedOnAnotherAndSave(Product baseProduct, Product targetProduct) {

        baseProduct.setName(targetProduct.getName());
        baseProduct.setDescription(targetProduct.getDescription());
        baseProduct.setPrice(targetProduct.getPrice());
        baseProduct.setPromoPrice(targetProduct.getPromoPrice());
        baseProduct.setImage(targetProduct.getImage());
        baseProduct.setShopCart(targetProduct.getShopCart());

        productRepository.save(baseProduct);
        productRepository.flush();


    }

}
