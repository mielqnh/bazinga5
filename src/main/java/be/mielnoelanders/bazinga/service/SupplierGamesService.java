package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.SupplierGames;

public interface SupplierGamesService {

    Iterable<SupplierGames> getAll();

    //getById()
    SupplierGames getOne(Long id);

    //delete()
    SupplierGames deleteById(Long id);

    //create()
    SupplierGames insertSupplierGames(SupplierGames supplierGames);

    //update()
    SupplierGames changeSupplierGames(Long id, SupplierGames supplierGames);

}
