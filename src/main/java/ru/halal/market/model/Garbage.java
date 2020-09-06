package ru.halal.market.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
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
    private boolean done;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private Set<ItemInGarbage> itemsInGarbage = new HashSet<>();

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

    public Set<ItemInGarbage> getItemsInGarbage() {
        return itemsInGarbage;
    }

    public void setItemsInGarbage(Set<ItemInGarbage> itemInGarbages) {
        this.itemsInGarbage = itemInGarbages;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Garbage{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", ordersDate=" + ordersDate +
                ", timeToOrderMustBeReady=" + timeToOrderMustBeReady +
                ", done=" + done +
                ", buyer=" + buyer +
                ", itemsInGarbage=" + itemsInGarbage +
                '}';
    }
}
