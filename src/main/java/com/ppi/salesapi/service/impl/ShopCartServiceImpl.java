package com.ppi.salesapi.service.impl;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.model.Product;
import com.ppi.salesapi.model.ShopCart;
import com.ppi.salesapi.repository.PaymentRepository;
import com.ppi.salesapi.repository.ShopCartRepository;
import com.ppi.salesapi.service.ProductService;
import com.ppi.salesapi.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartRepository shopCartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Long createShopCart() {

        ShopCart shopCart = new ShopCart();

        return shopCart.getId();

    }

    @Override
    public void addProductToShopCart(Long idCart, Long idProduct) {

        Assert.notNull(idCart, "IdCart cannot be null");
        Assert.notNull(idProduct, "IdProduct cannot be null");

        Product product = productService.findProductById(idProduct);

        if (product == null) {
            throw new RuntimeException("Product not found for id=" + idProduct);
        }

        ShopCart shopCart = this.findShopCartById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);

        }

        List<Product> shopCartProducts = shopCart.getProduct();
        shopCartProducts.add(product);

        shopCart.setProduct(shopCartProducts);

        shopCartRepository.saveAndFlush(shopCart);

    }

    @Override
    public void addPaymentMethodToCart(Long idPayment, Long idCart) {

        Assert.notNull(idPayment, "IdPayment cannot be null");
        Assert.notNull(idCart, "IdCart cannot be null");

        Payment payment = paymentRepository.findPaymentById(idPayment);

        if (payment == null) {
            throw new RuntimeException("Payment not found for id=" + idPayment);
        }

        ShopCart shopCart = shopCartRepository.findShopCartById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);
        }

        shopCart.setPayment(payment);

        shopCartRepository.saveAndFlush(shopCart);
    }

    public void addPaymentMethodToCart_(Payment payment, Long idCart) {

        Assert.notNull(payment, "payment cannot be null");
        Assert.notNull(idCart, "IdCart cannot be null");

        Payment cartPayment = new Payment();

        cartPayment.setNumber(payment.getNumber());
        cartPayment.setName(payment.getName());
        cartPayment.setCvv(payment.getCvv());
        cartPayment.setDate(payment.getDate());

        ShopCart shopCart = shopCartRepository.findShopCartById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);
        }

        shopCart.setPayment(cartPayment);

        shopCartRepository.saveAndFlush(shopCart);
    }


    @Override
    public void addAddressToShopCart(Long idCart, Address address) {

        Assert.notNull(idCart, "IdCart cannot be null");
        Assert.notNull(address, "Address cannot be null");

        ShopCart shopCart = this.findShopCartById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("shopCart not found for id=" + idCart);
        }

        Address addressCart = new Address();

        addressCart.setCity(address.getCity());
        addressCart.setStreet(address.getStreet());
        addressCart.setNumber(address.getNumber());
        addressCart.setAdditional(address.getAdditional());
        addressCart.setState(address.getState());

        shopCart.setAddress(addressCart);

    }


    public ShopCart findShopCartById(Long id) {

        return shopCartRepository.findShopCartById(id);

    }


}
