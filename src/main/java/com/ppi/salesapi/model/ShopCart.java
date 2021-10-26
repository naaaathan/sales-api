package com.ppi.salesapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class ShopCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn
    private List<Product> product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Payment payment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Address address;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", productList=" + product +
                ", payment=" + payment +
                ", address=" + address +
                '}';
    }
}
