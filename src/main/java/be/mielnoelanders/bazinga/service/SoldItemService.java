package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;

public interface SoldItemService {

    // --> create
    SalesReceipt addOne(SalesReceipt salesReceipt);

    // --> read
    Iterable<SalesReceipt> findAll();

    SalesReceipt findOneById(Long id);

    // --> update
    SalesReceipt updateOneById(Long id, SalesReceipt salesReceipt);

    // --> delete
    SalesReceipt deleteOneById(Long id);
}
