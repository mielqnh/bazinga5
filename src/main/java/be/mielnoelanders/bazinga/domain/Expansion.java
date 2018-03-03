package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Component
public class Expansion implements Serializable {

    // FIELDS
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="game_id",nullable = false)
    private Game expandedGame;

    private String expansionNumber;
    private String title;
    private int edition;
    private double normalPrice;
    private int promotionPercentage;
    private double actualPrice;
    private Publisher publisher;
    private int stock;
    private Supplier supplier;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
