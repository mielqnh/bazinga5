package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.enums.AccessoryType;
import be.mielnoelanders.bazinga.domain.other.Publisher;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Accessory extends Item {

    // FIELDS
    private String name;
    private AccessoryType type;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // CONSTRUCTORS
    public Accessory() {
    }

    private Accessory(Accessory.Builder builder) {
        name = builder.name;
        type = builder.type;
        publisher = builder.publisher;
    }

    // GETTERS & SETTERS

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AccessoryType getType() {
        return type;
    }
    public void setType(AccessoryType type) {
        this.type = type;
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
        return "Accessory{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", publisher=" + publisher +
                '}';
    }

    // BUILDER
    public static final class Builder {
        private String name;
        private Publisher publisher;
        private AccessoryType type;

        public Builder() {
        }

        public Accessory.Builder name(String val) {
            name = val;
            return this;
        }

        public Accessory.Builder type(AccessoryType val) {
            type = val;
            return this;
        }

        public Accessory.Builder publisher(Publisher val) {
            publisher = val;
            return this;
        }

        public Accessory build() {
            return new Accessory(this);
        }
    }
}

