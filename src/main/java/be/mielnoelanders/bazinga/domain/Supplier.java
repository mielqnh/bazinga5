package be.mielnoelanders.bazinga.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Supplier extends AbstractEntity implements Serializable{

    private static final long serialVersionUID =1L;

    // FIELDS
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;
    private String website;

    // FIELDS WITH MAPPINGS
    @ManyToMany(mappedBy = "suppliers")
    private List<Game> games;

    // CONSTRUCTORS
    public Supplier(){}

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
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
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
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
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", website='" + website + '\'' +
                ", games=" + games +
                '}';
    }
}
