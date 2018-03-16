package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.InStoreItem;

public interface InStoreItemService {

    // --> create
    InStoreItem addOne(InStoreItem inStoreItem);

    // --> read
    Iterable<InStoreItem> findAll();

    InStoreItem findOneById(Long id);

    // --> update
    InStoreItem updateOneById(Long id, InStoreItem inStoreItem);

    // --> delete
    InStoreItem deleteOneById(Long id);
}
