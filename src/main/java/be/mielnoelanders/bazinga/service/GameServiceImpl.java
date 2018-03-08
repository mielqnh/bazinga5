package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository repository;

    @PostConstruct
    public void init(){

        Game.Builder game1init = new Game.Builder();
        game1init.title("Dit is game 1").edition(1);
        Game game1 = game1init.build();

        Game.Builder game2init = new Game.Builder();
        game2init.title("Dit is game 2").edition(2);
        Game game2 = game2init.build();

        Game.Builder game3init = new Game.Builder();
        game3init.title("Dit is game 3").edition(3);
        Game game3 = game3init.build();

        this.repository.saveAll(Arrays.asList(game1,game2,game3));
    }

    @Override
    public Iterable<Game> getAll() {
        return repository.findAll();
    }

    @Override
    public Game getOne(Long id) {
        Optional<Game> result = repository.findById(id);
        if(result.isPresent()){
            Game game = result.get();
            System.out.println(game);
            return game;
        }else{
         return null;
        }
    }

    @Override
    public Game deleteById(Long id) {
        Game game = getOne(id);
        repository.deleteById(id);
        return game;
    }

    @Override
    public Game insertGame(Game game) {
        return repository.save(game);
    }

    @Override
    public Game changeGame(Long id, Game game) {

        Optional<Game> victim = repository.findById(id);

        if (victim.isPresent()) {
            Game test = victim.get();
            test.setTitle(game.getTitle());
            return repository.save(test);
        } else {
            return null;
        }
    }

    @Override
    public Iterable<Game> findByTitle(String name){
        return repository.findByTitle(name);
    }

}

