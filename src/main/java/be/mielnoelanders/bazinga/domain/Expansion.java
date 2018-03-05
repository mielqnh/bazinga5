package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Component
public class Expansion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    // FIELDS
    private String title;
    private int edition;
    private Publisher publisher;
    private int stock;
    private Supplier supplier;
    private Price price;

    // FIELDS WITH MAPPINGS
    @Column(name = "EXPANDS")
    @ManyToOne
    @JoinColumn(name="id")
    private Game expandedGame;

    // CONSTRUCTORS
    public Expansion(){}

    // GETTERS & SETTERS


    // OVERRIDES

}
