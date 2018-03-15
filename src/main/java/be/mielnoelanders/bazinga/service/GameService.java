package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;

public interface GameService {

    // --> create
    Game saveGame(Game game);

    // --> read
    Iterable<Game> findAllGames();

    Game findGameById(Long id);

    // --> update
    Game updateGame(Long id, Game game);

    // --> delete
    Game deleteGameById(Long id);

    // --> others
    Iterable<Game> findGameByTitle(String title);
}