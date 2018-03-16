package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Expansion extends Item {

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
    private Game(Game.Builder builder) {
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

    public static final class Builder {
        private String title;
        private int edition;
        private Publisher publisher;
        private List<Expansion> expansions;

        public Builder() {
        }

        public Game.Builder title(String val) {
            title = val;
            return this;
        }

        public Game.Builder edition(int val) {
            edition = val;
            return this;
        }

        public Game.Builder publisher(Publisher val) {
            publisher = val;
            return this;
        }

        public Game.Builder expansions(List<Expansion> val) {
            expansions = val;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }








     // FIELDS
    private String title;
    private int edition;
    private Publisher publisher;
    private int stock;
    private Supplier supplier;

    // FIELDS WITH MAPPINGS
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game expandedGame;

//    // CONSTRUCTORS
//    public Expansion(){}

    // GETTERS & SETTERS


    // OVERRIDES

}

/* to be replaced by purchasePrice
    private Price price;

    @Column(name = "PURCHASE_PRICE")
    @NotNull
    private double purchasePrice;
*/