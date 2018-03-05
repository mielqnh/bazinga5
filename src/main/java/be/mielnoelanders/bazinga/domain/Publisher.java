package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Publisher extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    // FIELDS
    private String name;
    private String website;

    // FIELDS WITH MAPPINGS
    @OneToMany(mappedBy = "publisher")
    private List<Game> games;

    // CONSTUCTORS
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
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }

// OVERRIDES
    @Override
    public String toString() {
        return "Publisher{" +
                "games=" + games +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
