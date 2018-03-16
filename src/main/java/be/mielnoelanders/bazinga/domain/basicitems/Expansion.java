package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.other.Publisher;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Expansion extends Item {

    // FIELDS
    private String title;
    private int edition;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    // CONSTRUCTORS
    public Expansion() {
    }
    private Expansion(Expansion.Builder builder) {
        title = builder.title;
        edition = builder.edition;
        publisher = builder.publisher;
        game = builder.game;
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
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", edition=" + edition +
                ", publisher=" + publisher +
                ", game=" + game +
                '}';
    }

    // BUILDER
    public static final class Builder {
        private String title;
        private int edition;
        private Publisher publisher;
        private Game game;

        public Builder() {
        }

        public Expansion.Builder title(String val) {
            title = val;
            return this;
        }

        public Expansion.Builder edition(int val) {
            edition = val;
            return this;
        }

        public Expansion.Builder publisher(Publisher val) {
            publisher = val;
            return this;
        }

        public Expansion.Builder game(Game val) {
            game = val;
            return this;
        }

        public Expansion build() {
            return new Expansion(this);
        }
    }
}