package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;

public interface GameService {

    //create()
    Game insertGame(Game game);

    //read()
    Iterable<Game> getAll();

    Game getOne(Long id);

    //update()
    Game changeGame(Long id, Game game);

    //delete()
    Game deleteById(Long id);

    Iterable<Game> findByTitle(String name);
}
