package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class SoldItem extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8471750286273301293L;

    // FIELDS
    private String date;

    // FIELDS WITH ANNOTATIONS
    @NotNull
    private double sellingPrice;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Item item;

    // CONSTRUCTORS
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // OVERRIDES

    @Override
    public String toString() {
        return "SoldItem{" +
                "date='" + date + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", customer=" + customer +
                ", item=" + item +
                '}';
    }
}
