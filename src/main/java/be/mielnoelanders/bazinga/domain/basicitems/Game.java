package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.other.Publisher;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Game extends Item {

    // FIELDS
    private String title;
    private int edition;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id")
    private Publisher publisher;

    // CONSTRUCTORS
    public Game(){}
    private Game(Builder builder) {
        title = builder.title;
        edition = builder.edition;
        publisher = builder.publisher;
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

    // OVERRIDES
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", edition=" + edition +
                ", publisher=" + publisher +
                '}';
    }

    // BUILDER
     public static final class Builder {
        private String title;
        private int edition;
        private Publisher publisher;

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

        public Game build() {
            return new Game(this);
        }
    }
}
