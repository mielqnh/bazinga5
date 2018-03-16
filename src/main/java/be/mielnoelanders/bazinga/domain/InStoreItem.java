package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class InStoreItem extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -1573209055880743815L;

    // FIELDS
    private String date;

    // FIELDS WITH ANNOTATIONS
    @NotNull
    private double purchasePrice;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Item item;

    // CONSTRUCTORS
    public InStoreItem(){}

    // GETTERS & SETTERS
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    // OVERRIDES
    @Override
    public String toString() {
        return "InStoreItem{" +
                "supplier=" + supplier +
                ", item=" + item +
                ", purchasePrice=" + purchasePrice +
                ", date='" + date + '\'' +
                '}';
    }
}
