package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Supplier;

public interface SupplierService {


// --> create (saveBla)



// --> read (findAllBla & findBlaById)



// --> update (updateBla)



// --> delete (deleteBlaById)



// --> others (findBlaByBuh)




    Supplier addOne(Supplier supplier);

    Iterable<Supplier> findAll();

    Supplier findById(Long id);

    Supplier updateOne(Long id, Supplier supplier);

    void deleteById(Long id);
}
