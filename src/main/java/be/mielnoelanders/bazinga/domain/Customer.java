package be.mielnoelanders.bazinga.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    // FIELDS
    private String name;
    private String firstName;

    private Address address;
    private double totalSpent;
    private boolean goodCustomer;
    private String email;
    private String phoneNumber;

    // FIELDS WITH MAPPINGS
    @ManyToMany(mappedBy = "customers")
    private List<Game> games;

    // CONSTRUCTORS
    public Customer(){}

    // GETTERS & SETTERS
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
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", games=" + games +
                ", totalSpent=" + totalSpent +
                ", goodCustomer=" + goodCustomer +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
