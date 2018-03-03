package be.mielnoelanders.bazinga.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Component
public class Customer implements Serializable {


    // FIELDS
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String firstName;

    @Autowired
    private Address address;

    @ManyToMany(mappedBy = "customers")
    private List<Game> games;

    private double totalSpent;
    private boolean goodCustomer;
    private String email;
    private String phoneNumber;

    // CONSTRUCTORS

    public Customer(){}

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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public double getTotalSpent() {
        return totalSpent;
    }
    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
    public boolean isGoodCustomer() {
        return goodCustomer;
    }
    public void setGoodCustomer(boolean goodCustomer) {
        this.goodCustomer = goodCustomer;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", games=" + games +
                ", totalSpent=" + totalSpent +
                ", goodCustomer=" + goodCustomer +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
