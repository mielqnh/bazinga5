package be.mielnoelanders.bazinga.domain.other;

import be.mielnoelanders.bazinga.domain.AbstractEntity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Publisher extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // FIELDS
    private String name;
    private String website;

    // CONSTRUCTORS
    public Publisher(){}

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        return "Publisher{" +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}