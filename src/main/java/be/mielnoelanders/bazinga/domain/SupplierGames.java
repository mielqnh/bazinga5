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

    // FIELDS WITH MAPPINGS
    @ManyToOne
    @JoinColumn(name="id")
    private Supplier supplier;

    @Column(name = "PURCHASE_PRICE")
    @NotNull
    private double purchasePrice;

}
