package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.CustomerGames;

public interface CustomerGamesService {

    Iterable<CustomerGames> getAll();

    //getById()
    CustomerGames getOne(Long id);

    //delete()
    CustomerGames deleteById(Long id);

    //create()
    CustomerGames insertCustomerGames(CustomerGames customerGames);

    //update()
    CustomerGames changeCustomerGames(Long id, CustomerGames customerGames);

}
