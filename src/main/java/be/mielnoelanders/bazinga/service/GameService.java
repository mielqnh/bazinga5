package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;

public interface GameService {

    // --> create
    Game addOne(Game game);

    // --> read
    Iterable<Game> findAll();

    Game findOneById(Long id);

    // --> update
    Game updateOneById(Long id, Game game);

    // --> delete
    Game deleteOneById(Long id);

    // --> others
    Iterable<Game> findOneByName(String name);
}