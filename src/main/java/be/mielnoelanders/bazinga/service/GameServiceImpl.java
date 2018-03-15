package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    // FIELDS
    private final GameRepository repository;

    // CONSTRUCTORS
    @Autowired
    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    // METHODS
    // --> init
    @PostConstruct
    public void init() {

        Publisher publisher = new Publisher();
        publisher.setName("FantasyFlightGames");
        publisher.setWebsite("http://www.fantasyflightgames.com");

        Address address = new Address();
        address.setPostalCode("3500");
        address.setNumber("254");
        address.setStreet("Boaty McBoatStreet");
        address.setCity("Hasselt");
        address.setCountry("Belgie");

        Customer customer = new Customer();
        customer.setFirstName("Boaty");
        customer.setName("McBoat");
        customer.setPhoneNumber("011 45 78 29");
        customer.setGoodCustomer(true);
        customer.setEmail("jefke@miel.be");
        customer.setAddress(address);

        Supplier supplier = new Supplier();
        supplier.setName("Enigma");
        supplier.setPhoneNumber("011 22 55 44");
        supplier.setEmail("info@enigma.be");
        supplier.setWebsite("www.enigma.be");

        Game.Builder game1init = new Game.Builder();
        game1init.title("Dit is game 1")
                .edition(1)
                .publisher(publisher);
        Game game1 = game1init.build();

        SoldItem soldItem = new SoldItem();
        soldItem.setDate("12/03/2018");
        soldItem.setGame(game1);
        soldItem.setSellingPrice(39.99);
        soldItem.setCustomer(customer);

        InStoreItem inStoreItem = new InStoreItem();
        inStoreItem.setSupplier(supplier);
        inStoreItem.setDate("05/03/2018");
        inStoreItem.setPurchasePrice(15.59);
        inStoreItem.setGame(game1);

        Game.Builder game2init = new Game.Builder();
        game2init.title("Dit is game 2").edition(2);
        Game game2 = game2init.build();

        Game.Builder game3init = new Game.Builder();
        game3init.title("Dit is game 3").edition(3);
        Game game3 = game3init.build();

        this.repository.saveAll(Arrays.asList(game1, game2, game3));
    }

    // --> create
    @Override
    public Game addOne(Game game) {
        return repository.save(game);
    }

    // --> read
    @Override
    public Iterable<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public Game findOneById(Long id) {
        Optional<Game> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public Game updateOneById(Long id, Game game) {
        Game gameToChange = findOneById(id);
        if (gameToChange == null) {
            return null;
        } else {
            gameToChange.setTitle(game.getTitle());
            return repository.save(gameToChange);
        }
    }

    // --> delete
    @Override
    public Game deleteOneById(Long id) {
        Game game = findOneById(id);
        if (game == null) {
            return null;
        } else {
            repository.deleteById(id);
            return game;
        }
    }

    // --> others
    @Override
    public Iterable<Game> findOneByTitle(String title) {
        return repository.findByTitle(title);
    }
}

