package be.mielnoelanders.bazinga.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

    // FIELD MET OVERERVING NAAR AL DE ANDERE DOMAINS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // GETTER
    public Long getId() {
        return id;
    }
}
