package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Component
public class Accessory implements Serializable {

    // FIELDS
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private AccessoryType type;
    private String series;
    private int edition;
    private double normalPrice;
    private int promotionPercentage;
    private double actualPrice;
    private Publisher publisher;
    private int stock;
    private Supplier supplier;

    // CONSTRUCTORS
    public Accessory(){}

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AccessoryType getType() {
        return type;
    }
    public void setType(AccessoryType type) {
        this.type = type;
    }
    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }
    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }
    public double getNormalPrice() {
        return normalPrice;
    }
    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }
    public int getPromotionPercentage() {
        return promotionPercentage;
    }
    public void setPromotionPercentage(int promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }
    public double getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", series='" + series + '\'' +
                ", edition=" + edition +
                ", normalPrice=" + normalPrice +
                ", promotionPercentage=" + promotionPercentage +
                ", actualPrice=" + actualPrice +
                ", publisher=" + publisher +
                ", stock=" + stock +
                ", supplier=" + supplier +
                '}';
    }
}
