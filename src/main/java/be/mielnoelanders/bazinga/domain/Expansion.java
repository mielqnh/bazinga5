package be.mielnoelanders.bazinga.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Expansion extends Item {

    private static final long serialVersionUID =1L;

     // FIELDS
    private String title;
    private int edition;
    private Publisher publisher;
    private int stock;
    private Supplier supplier;

    // FIELDS WITH MAPPINGS
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game expandedGame;

//    // CONSTRUCTORS
//    public Expansion(){}

    // GETTERS & SETTERS


    // OVERRIDES

}

/* to be replaced by purchasePrice
    private Price price;

    @Column(name = "PURCHASE_PRICE")
    @NotNull
    private double purchasePrice;
*/