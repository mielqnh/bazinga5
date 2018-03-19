package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;

public interface InStoreItemService {

    // --> create
    PurchaseReceipt addOne(PurchaseReceipt purchaseReceipt);

    // --> read
    Iterable<PurchaseReceipt> findAll();

    PurchaseReceipt findOneById(Long id);

    // --> update
    PurchaseReceipt updateOneById(Long id, PurchaseReceipt purchaseReceipt);

    // --> delete
    PurchaseReceipt deleteOneById(Long id);
}
