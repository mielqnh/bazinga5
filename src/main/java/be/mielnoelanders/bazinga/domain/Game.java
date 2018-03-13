package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Game extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    // FIELDS
    private String title;
    private int edition;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id")
    private Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expandedGame")
    private List<Expansion> expansions;

    // CONSTRUCTORS
    public Game(){}
    private Game(Builder builder) {
        title = builder.title;
        edition = builder.edition;
        publisher = builder.publisher;
        expansions = builder.expansions;
    }

    // GETTERS & SETTERS
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
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public List<Expansion> getExpansions() {
        return expansions;
    }
    public void setExpansions(List<Expansion> expansions) {
        this.expansions = expansions;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", edition=" + edition +
                ", publisher=" + publisher +
                ", expansions=" + expansions +
                '}';
    }

    // STATIC INNER CLASS BUILDER (Als ik het goed begrijp zorgt die ervoor dat je instanties kan aanmaken die bepaalde kenmerken wel of net niet hebben.
    // Zo heeft bijvoorbeeld niet elke game expansions, dus zou je die bij het aanmaken kunnen weglaten en heb je dus een game zonder het expansion-field.
    public static final class Builder {
        private String title;
        private int edition;
        private Publisher publisher;
        private List<Expansion> expansions;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder edition(int val) {
            edition = val;
            return this;
        }

        public Builder publisher(Publisher val) {
            publisher = val;
            return this;
        }

        public Builder expansions(List<Expansion> val) {
            expansions = val;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

}

// FIELDS WITH MANYTOMANYMAPPINGS (FOUT)
/*  @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Game_Customer",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Game_Supplier",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    private List<Supplier> suppliers;*/