package com.ppi.salesapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double totalValue;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn
    private List<Product> product;

    @OneToOne
    @JoinColumn
    private Payment payment;

    @OneToOne
    @JoinColumn
    private Address address;

    @OneToOne
    @JoinColumn
    private User user;

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

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
