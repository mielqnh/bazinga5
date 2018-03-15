package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.SoldItem;

public interface SoldItemService {



    SoldItem saveCustomerGames(SoldItem soldItem);

// --> create (addOne)



// --> read (findAll & findOneById)



// --> update (updateOneById)



// --> delete (deleteOneById)



// --> others (Bla)




    Iterable<SoldItem> getAll();

    //getById()
    SoldItem getOne(Long id);

    //delete()
    SoldItem deleteById(Long id);


    //update()
    SoldItem changeCustomerGames(Long id, SoldItem soldItem);

}
