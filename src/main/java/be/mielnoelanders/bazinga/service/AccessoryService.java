package be.mielnoelanders.bazinga.service;


import be.mielnoelanders.bazinga.domain.basicitems.Accessory;

public interface AccessoryService {

    // --> create
    Accessory addOne(Accessory accessory);

    // --> read
    Iterable<Accessory> findAll();

    Accessory findOneById(Long id);

    // --> update
    Accessory updateOneById(Long id, Accessory accessory);

    // --> delete
    Accessory deleteOneById(Long id);

    // --> others
    Iterable<Accessory> findOneByName(String name);
}