package com.ppi.salesapi.service;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.ShopCart;

public interface ShopCartService {

    ShopCart findShopCartById(Long id);

    Long createShopCart();

    void addProductToShopCart(Long idCart, Long idProduct);

    void addPaymentMethodToCart(Long idPayment, Long idCart);

    void addAddressToShopCart(Long idCart,Address address);
}
