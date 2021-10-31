package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.model.User;
import com.ppi.salesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    private User findUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    private Long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/addPayment/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private void addPaymentToUser(@RequestBody Payment payment, @PathVariable(value = "userId") Long userId) {

        userService.addPaymentToUser(payment, userId);

    }

    @PostMapping("/addAddress/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private void addAddressToUser(@RequestBody Address address, @PathVariable(value = "userId") Long userId) {

        userService.addAddressToUser(address, userId);

    }


}
