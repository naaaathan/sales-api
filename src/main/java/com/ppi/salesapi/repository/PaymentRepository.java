package com.ppi.salesapi.repository;

import com.ppi.salesapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findPaymentById(Long id);


}
