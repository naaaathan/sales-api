package com.ppi.salesapi.service;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.model.User;

public interface UserService {

    User getUserByUsername(String username);

    Long createUser(User user);

    void addPaymentToUser(Payment payment, Long userId);

    void addAddressToUser(Address address, Long userId);
}
