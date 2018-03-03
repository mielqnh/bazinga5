package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Component
public class Game implements Serializable {

    // FIELDS
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String title;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Game_Customer",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Game_Supplier",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    private List<Supplier> suppliers;

    private int edition;

    @OneToMany(mappedBy = "expansion")
    private List<Expansion> expansions;

    @NotNull
    private double normalPrice;
    @NotNull
    private int promotionPercentage;
    @NotNull
    private double actualPrice;

    @ManyToOne
    @JoinColumn(name="publisher_id",nullable = false)
    private Publisher publisher;

    private Supplier supplier;
    private int stock;

    // CONSTRUCTORS
    public Game(){}

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }
    public List<Expansion> getExpansions() {
        return expansions;
    }
    public void setExpansions(List<Expansion> expansions) {
        this.expansions = expansions;
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
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public List<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", customers=" + customers +
                ", suppliers=" + suppliers +
                ", edition=" + edition +
                ", expansions=" + expansions +
                ", normalPrice=" + normalPrice +
                ", promotionPercentage=" + promotionPercentage +
                ", actualPrice=" + actualPrice +
                ", publisher=" + publisher +
                ", stock=" + stock +
                ", supplier=" + supplier +
                '}';
    }
}
