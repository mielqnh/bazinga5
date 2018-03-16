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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    // CONSTRUCTORS
    public Expansion() {
    }
    private Expansion(Expansion.Builder builder) {
        title = builder.title;
        game = builder.game;
    }

    // GETTERS & SETTERS
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
                ", game=" + game +
                '}';
    }

    // BUILDER
    public static final class Builder {
        private String title;
        private Publisher publisher;
        private Game game;

        public Builder() {
        }
        public Expansion.Builder title(String val) {
            title = val;
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