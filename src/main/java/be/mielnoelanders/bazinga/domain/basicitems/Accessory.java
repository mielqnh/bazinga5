package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.enums.AccessoryType;

import javax.persistence.Entity;

@Entity
public class Accessory extends Item {

    // FIELDS
    private AccessoryType type;

    // CONSTRUCTORS
    public Accessory() {
    }

    // GETTERS & SETTERS
    public AccessoryType getType() {
        return type;
    }
    public void setType(AccessoryType type) {
        this.type = type;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return super.toString()+"Accessory{" +
                ", type=" + type +
                '}';
    }
}

