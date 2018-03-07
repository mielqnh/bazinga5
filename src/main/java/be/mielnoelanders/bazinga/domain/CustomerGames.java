package be.mielnoelanders.bazinga.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class CustomerGames extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8471750286273301293L;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @NotNull
    private double sellingPrice;
    private String date;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CustomerGames{" +
                "game=" + game +
                ", sellingPrice=" + sellingPrice +
                ", date='" + date + '\'' +
                '}';
    }
}
