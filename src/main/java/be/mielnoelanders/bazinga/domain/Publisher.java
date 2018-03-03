package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Component
public class Publisher implements Serializable {

    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "publisher")// Naam van de klasse
    private List<Game> games;

    private String name;
    private String website;

    // CONSTUCTORS
    public Publisher(){}

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
