package be.mielnoelanders.bazinga.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

// comment in WmTestCustomerComment

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private List<CustomerGames> customerGames;

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
    public List<CustomerGames> getCustomerGames() {
        return customerGames;
    }
    public void setCustomerGames(List<CustomerGames> customerGames) {
        this.customerGames = customerGames;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", totalSpent=" + totalSpent +
                ", goodCustomer=" + goodCustomer +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerGames=" + customerGames +
                '}';
    }
}
