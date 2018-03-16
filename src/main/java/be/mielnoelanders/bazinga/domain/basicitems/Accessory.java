package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.enums.AccessoryType;

import javax.persistence.Entity;

@Entity
public class Accessory extends Item {

    // FIELDS
    private String name;
    private AccessoryType type;

    // CONSTRUCTORS
    public Accessory() {
    }

    private Accessory(Accessory.Builder builder) {
        name = builder.name;
        type = builder.type;
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

    // OVERRIDES
    @Override
    public String toString() {
        return "Accessory{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    // BUILDER
    public static final class Builder {
        private String name;
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
        public Accessory build() {
            return new Accessory(this);
        }
    }
}

