package be.mielnoelanders.bazinga.domain.basicitems;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Expansion extends Item {

    // FIELDS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    // CONSTRUCTORS
    public Expansion() {
    }

    // GETTERS & SETTERS
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return super.toString()+"Game{" +
                ", game=" + game +
                '}';
    }

}