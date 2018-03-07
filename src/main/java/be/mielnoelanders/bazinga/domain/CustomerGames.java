package be.mielnoelanders.bazinga.domain;

/* Deze klasse vangt de many-to-many op tussen game en customer.
 * Het is bedoeld als tussentabel.
 * Deze klasse heet eigenlijk "GameGekochtDoor"
 */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class CustomerGames extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @NotNull
    private double sellingPrice;

    //Misschien datum

}
