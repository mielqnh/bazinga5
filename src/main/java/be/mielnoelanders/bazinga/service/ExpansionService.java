package be.mielnoelanders.bazinga.service;


import be.mielnoelanders.bazinga.domain.basicitems.Expansion;

public interface ExpansionService {

    // --> create
    Expansion addOne(Expansion expansion);

    // --> read
    Iterable<Expansion> findAll();

    Expansion findOneById(Long id);

    // --> update
    Expansion updateOneById(Long id, Expansion expansion);

    // --> delete
    Expansion deleteOneById(Long id);

    // --> others
    Iterable<Expansion> findOneByName(String name);
}