package be.mielnoelanders.bazinga.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Customer extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // FIELDS
    private double totalSpent;
    private boolean goodCustomer;
    private String email;
    private String phoneNumber;

    // FIELDS WITH ANNOTATIONS
    @NotNull
    private String name;
    @NotNull
    private String firstName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    // CONSTRUCTORS
    public Customer() {
    }

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


    // OVERRIDES
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + super.getId() + '\'' +
                ", totalSpent=" + totalSpent +
                ", goodCustomer=" + goodCustomer +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                '}';
    }
}
