package be.mielnoelanders.bazinga.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

    // FIELD MET OVERERVING NAAR AL DE ANDERE DOMAINS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // GETTER
    public Long getId() {
        return id;
    }
}
