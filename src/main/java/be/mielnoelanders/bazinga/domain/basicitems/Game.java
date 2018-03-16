package be.mielnoelanders.bazinga.domain.basicitems;

import javax.persistence.Entity;

@Entity
public class Game extends Item {

    // FIELDS
    private String title;
    private int edition;

    // CONSTRUCTORS
    public Game(){}
    private Game(Builder builder) {
        title = builder.title;
        edition = builder.edition;
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

    // OVERRIDES
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", edition=" + edition +
                '}';
    }

    // BUILDER
     public static final class Builder {
        private String title;
        private int edition;

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
        public Game build() {
            return new Game(this);
        }
    }
}
