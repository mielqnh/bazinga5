package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.SoldItem;

public interface SoldItemService {


    // --> create (saveBla)
    SoldItem saveCustomerGames(SoldItem soldItem);


// --> read (findAllBla & findBlaById)



// --> update (updateBla)



// --> delete (deleteBlaById)



// --> others (findBlaByBuh)




    Iterable<SoldItem> getAll();

    //getById()
    SoldItem getOne(Long id);

    //delete()
    SoldItem deleteById(Long id);


    //update()
    SoldItem changeCustomerGames(Long id, SoldItem soldItem);

}
