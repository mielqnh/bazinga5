package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SoldItem;

public interface SoldItemService {

    // --> create
    SoldItem addOne(SoldItem soldItem);

    // --> read
    Iterable<SoldItem> findAll();

    SoldItem findOneById(Long id);

    // --> update
    SoldItem updateOneById(Long id, SoldItem soldItem);

    // --> delete
    SoldItem deleteOneById(Long id);
}
