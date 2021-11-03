package com.ppi.salesapi.service.impl;

import com.ppi.salesapi.model.*;
import com.ppi.salesapi.repository.AddressRepository;
import com.ppi.salesapi.repository.PaymentRepository;
import com.ppi.salesapi.repository.SellRepository;
import com.ppi.salesapi.repository.UserRepository;
import com.ppi.salesapi.service.ProductService;
import com.ppi.salesapi.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addProductToShopCart(Long idCart, Long idProduct) {

        Assert.notNull(idCart, "IdCart cannot be null");
        Assert.notNull(idProduct, "IdProduct cannot be null");

        Product product = productService.findProductById(idProduct);

        if (product == null) {
            throw new RuntimeException("Product not found for id=" + idProduct);
        }

        Sell shopCart = this.findSellById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);

        }

        List<Product> shopCartProducts = shopCart.getProduct();
        shopCartProducts.add(product);

        shopCart.setProduct(shopCartProducts);

        sellRepository.saveAndFlush(shopCart);

    }

    @Override
    public void addPaymentMethodToCart(Long idPayment, Long idCart) {

        Assert.notNull(idPayment, "IdPayment cannot be null");
        Assert.notNull(idCart, "IdCart cannot be null");

        Payment payment = paymentRepository.findPaymentById(idPayment);

        if (payment == null) {
            throw new RuntimeException("Payment not found for id=" + idPayment);
        }

        Sell shopCart = sellRepository.findSellById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);
        }

        shopCart.setPayment(payment);

        sellRepository.saveAndFlush(shopCart);
    }

    public void addPaymentMethodToCart_(Payment payment, Long idCart) {

        Assert.notNull(payment, "payment cannot be null");
        Assert.notNull(idCart, "IdCart cannot be null");

        Payment cartPayment = new Payment();

        cartPayment.setNumber(payment.getNumber());
        cartPayment.setName(payment.getName());
        cartPayment.setCvv(payment.getCvv());
        cartPayment.setDate(payment.getDate());

        Sell shopCart = sellRepository.findSellById(idCart);

        if (shopCart == null) {
            throw new RuntimeException("ShopCart not found for id=" + idCart);
        }

        shopCart.setPayment(cartPayment);

        sellRepository.saveAndFlush(shopCart);
    }


    @Override
    public void addAddressToShopCart(Long idCart, Address address) {

        Assert.notNull(idCart, "IdCart cannot be null");
        Assert.notNull(address, "Address cannot be null");

        Sell shopCart = this.findSellById(idCart);

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

        addressRepository.saveAndFlush(addressCart);
        sellRepository.saveAndFlush(shopCart);

    }


    public Sell findSellById(Long id) {

        return sellRepository.findSellById(id);

    }


    public Long concludeSell(Sell sell) {

        Assert.notNull(sell, "Sell Object cannot be null");
        Assert.notNull(sell.getAddress(), "Address cannot be null");
        Assert.notNull(sell.getPayment(), "Payment cannot be null");
        Assert.notNull(sell.getProduct(), "Products cannot be null");
        Assert.notNull(sell.getUser(), "User cannot be null");

        addressRepository.saveAndFlush(sell.getAddress());
        paymentRepository.saveAndFlush(sell.getPayment());

        populateUserDefaults(sell);

        sell.setTotalValue(calculateTotalValue(sell));

        sellRepository.saveAndFlush(sell);

        return sell.getId();
    }

    private void populateUserDefaults(Sell sell) {
        User user = sell.getUser();
        List<Address> addressList = user.getAddress();
        addressList.add(sell.getAddress());
        List<Payment> paymentList = user.getPayment();
        paymentList.add(sell.getPayment());

        user.setPayment(paymentList);
        user.setAddress(addressList);

        userRepository.saveAndFlush(user);

    }

    private double calculateTotalValue(Sell sell) {

        return sell.getProduct().stream().mapToDouble(Product::getPromoPrice).sum();

    }


}

