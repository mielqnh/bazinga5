package be.mielnoelanders.bazinga.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
public enum AccessoryType {
    SLEEVES, DICE, PLAYMATS, DECKBOXES, BINDERS, BINDERPAGES, OTHERS
}
