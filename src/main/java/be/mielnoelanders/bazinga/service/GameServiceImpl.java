package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository repository;

    @PostConstruct
    public void init() {

        // Deze is gemakkelijk, geen composition.
        Publisher publisher = new Publisher();
        publisher.setName("FantasyFlightGames");
        publisher.setWebsite("http://www.fantasyflightgames.com");

        // Die wil een list van supplierGames, maar die is er niet.
        Supplier supplier = new Supplier();
        supplier.setName("Enigma");
        supplier.setPhoneNumber("011 22 55 44");
        supplier.setEmail("infoéenigma.be");
        supplier.setWebsite("www.enigma.be");
        ? supplier.setSupplierGames(new ArrayList<>());
        supplier.setAddress

        // Deze neemt een game, maar je hebt deze net nodig om een game te maken.
        CustomerGames customerGames = new CustomerGames();
        customerGames.setDate("12/03/2018");
        ? customerGames.setGame(putyourgamehere);
        customerGames.setSellingPrice(39.99);

        // Idem
        SupplierGames  supplierGames = new SupplierGames();
        supplierGames.setSupplier(supplier);
        supplierGames.setDate("05/03/2018");
        ? supplierGames.setGame(putyourgamehere);
        supplierGames.setPurchasePrice(15.59);


        Game.Builder game1init = new Game.Builder();
        game1init.title("Dit is game 1")
                .edition(1)
                .publisher(publisher)
                .customerGames(customerGames)
                .supplierGames(supplierGames);
        Game game1 = game1init.build();

        Game.Builder game2init = new Game.Builder();
        game2init.title("Dit is game 2").edition(2);
        Game game2 = game2init.build();

        Game.Builder game3init = new Game.Builder();
        game3init.title("Dit is game 3").edition(3);
        Game game3 = game3init.build();

        this.repository.saveAll(Arrays.asList(game1, game2, game3));
    }

    @Override
    public Iterable<Game> getAll() {
        return repository.findAll();
    }

    @Override
    public Game getOne(Long id) {
        Optional<Game> result = repository.findById(id);
        if (result.isPresent()) {
            Game game = result.get();
            return game;
        } else {
            return null;
        }
    }

    @Override
    public Game deleteById(Long id) {
        Game game = getOne(id);
        if (game == null) {
            return null;
        } else {
            repository.deleteById(id);
            return game;
        }
    }

    @Override
    public Game insertGame(Game game) {
        return repository.save(game);
    }

    @Override
    public Game changeGame(Long id, Game game) {
        Game gameToChange = getOne(id);
        if(gameToChange == null){
            return null;
        }else{
            gameToChange.setTitle(game.getTitle());
            return repository.save(gameToChange);
        }
    }

    @Override
    public Iterable<Game> findByTitle(String name) {
        return repository.findByTitle(name);
    }

}

