package be.mielnoelanders.bazinga.domain;

        /* Deze klasse vangt de many-to-many op tussen game en supplier.
         * Het is bedoeld als tussentabel.
         * Deze klasse heet eigenlijk "GameBeschikbaarBij"
         */

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.JoinColumn;
        import javax.persistence.ManyToOne;
        import javax.validation.constraints.NotNull;
        import java.io.Serializable;

@Entity
public class SupplierGames extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    @Column(name = "PURCHASE_PRICE")
    @NotNull
    private double purchasePrice;

    // FIELDS WITH MAPPINGS
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

}
