package ru.halal.market.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
public class Garbage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private double price;
    private Calendar ordersDate;
    private Calendar timeToOrderMustBeReady;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private Set<ItemInGarbage> itemInGarbages;

    public Garbage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Calendar getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Calendar ordersDate) {
        this.ordersDate = ordersDate;
    }

    public Calendar getTimeToOrderMustBeReady() {
        return timeToOrderMustBeReady;
    }

    public void setTimeToOrderMustBeReady(Calendar timeToOrderMustBeReady) {
        this.timeToOrderMustBeReady = timeToOrderMustBeReady;
    }

    public Set<ItemInGarbage> getItemInGarbages() {
        return itemInGarbages;
    }

    public void setItemInGarbages(Set<ItemInGarbage> itemInGarbages) {
        this.itemInGarbages = itemInGarbages;
    }
}
