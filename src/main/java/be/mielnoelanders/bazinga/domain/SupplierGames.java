package be.mielnoelanders.bazinga.domain;

/* Deze klasse vangt de many-to-many op tussen game en supplier.
 * Het is bedoeld als tussentabel.
 * Deze klasse heet eigenlijk "GameBeschikbaarBij"
 */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class SupplierGames extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -1573209055880743815L;

    // FIELDS WITH MAPPINGS
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    // FIELDS
    @NotNull
    private double purchasePrice;
    private String date;

    // CONSTRUCTORS
    public SupplierGames(){}

    // GETTERS & SETTERS
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "SupplierGames{" +
                "supplier=" + supplier +
                ", game=" + game +
                ", purchasePrice=" + purchasePrice +
                ", date='" + date + '\'' +
                '}';
    }
}
