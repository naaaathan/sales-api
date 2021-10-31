package com.ppi.salesapi.service.impl;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.model.User;
import com.ppi.salesapi.repository.AddressRepository;
import com.ppi.salesapi.repository.PaymentRepository;
import com.ppi.salesapi.repository.UserRepository;
import com.ppi.salesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public User getUserByUsername(String username) {

        Assert.notNull(username, "Username cannot be null");

        return userRepository.findUserByUsername(username);
    }

    @Override
    public Long createUser(User user) {

        Assert.notNull(user, "User cannot be null");

        userRepository.saveAndFlush(user);

        return user.getId();
    }

    @Override
    public void addPaymentToUser(Payment payment, Long userId) {

        Assert.notNull(payment, "Payment cannot be null");

        paymentRepository.saveAndFlush(payment);

        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found for id=" + userId);
        }

        List<Payment> paymentList = user.getPayment();

        paymentList.add(payment);

        user.setPayment(paymentList);

        userRepository.saveAndFlush(user);

    }


    @Override
    public void addAddressToUser(Address address, Long userId) {

        Assert.notNull(address, "Payment cannot be null");

        addressRepository.saveAndFlush(address);

        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found for id=" + userId);
        }

        List<Address> addressList = user.getAddress();

        addressList.add(address);

        user.setAddress(addressList);

        userRepository.saveAndFlush(user);

    }
}
