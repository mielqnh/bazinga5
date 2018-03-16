package be.mielnoelanders.bazinga.domain.other;

import be.mielnoelanders.bazinga.domain.AbstractEntity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Supplier extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -5872135512196851021L;

    // FIELDS
    private String name;
    private String email;
    private String phoneNumber;
    private String website;

    // CONSTRUCTORS
    public Supplier() {
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
