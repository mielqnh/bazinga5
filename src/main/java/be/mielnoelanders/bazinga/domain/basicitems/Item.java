package be.mielnoelanders.bazinga.domain.basicitems;

import be.mielnoelanders.bazinga.domain.AbstractEntity;
import be.mielnoelanders.bazinga.domain.other.Publisher;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item extends AbstractEntity implements Serializable {

    private static final long serialVersionUID =1L;

    // FIELDS
    private String name;

    // FIELDS WITH MAPPINGS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
