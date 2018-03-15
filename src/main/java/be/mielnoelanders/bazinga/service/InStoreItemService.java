package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.InStoreItem;

public interface InStoreItemService {


// --> create (saveBla)



// --> read (findAllBla & findBlaById)



// --> update (updateBla)



// --> delete (deleteBlaById)



// --> others (findBlaByBuh)






    Iterable<InStoreItem> getAll();

    //getById()
    InStoreItem getOne(Long id);

    //delete()
    InStoreItem deleteById(Long id);

    //create()
    InStoreItem insertSupplierGames(InStoreItem inStoreItem);

    //update()
    InStoreItem changeSupplierGames(Long id, InStoreItem inStoreItem);

}
