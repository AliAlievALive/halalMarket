package ru.halal.market.model;

import javax.persistence.*;

@Entity
public class ItemInGarbage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name="product_id")
    private Product product;

    private int count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "garbage_id")
    private Garbage purchase;

    public ItemInGarbage() {
    }

    public ItemInGarbage(Product product, int count, Garbage purchase) {
        this.product = product;
        this.count = count;
        this.purchase = purchase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Garbage getPurchase() {
        return purchase;
    }

    public void setPurchase(Garbage garbage) {
        this.purchase = garbage;
    }

    @Override
    public String toString() {
        return "ItemInGarbage{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", purchase=" + purchase +
                '}';
    }
}
