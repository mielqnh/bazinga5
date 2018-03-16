package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.other.Supplier;

public interface SupplierService {

    // --> create
    Supplier addOne(Supplier supplier);

    // --> read
    Iterable<Supplier> findAll();

    Supplier findOneById(Long id);

    // --> update
    Supplier updateOneById(Long id, Supplier supplier);

    // --> delete (deleteOneById)
    void deleteOneById(Long id);
}
