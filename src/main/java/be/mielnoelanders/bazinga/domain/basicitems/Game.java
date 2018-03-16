package be.mielnoelanders.bazinga.domain.basicitems;

import javax.persistence.Entity;

@Entity
public class Game extends Item {

    // FIELDS
    private int edition;

    // CONSTRUCTORS
    public Game(){}

    // GETTERS & SETTERS
    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return super.toString()+"Game{" +
                ", edition=" + edition +
                '}';
    }
}
