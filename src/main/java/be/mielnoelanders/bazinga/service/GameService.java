package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;

public interface GameService {

    Iterable<Game> getAll();

    //getById()
    Game getOne(Long id);

    //delete()
    void deleteById(Long id);

    //create()
    Game insertGame(Game game);

    //update()
    Game changeGame(Long id, Game game);

    Iterable<Game> findByName(String name);

}
