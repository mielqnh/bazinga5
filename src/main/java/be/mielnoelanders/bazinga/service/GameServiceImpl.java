package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import be.mielnoelanders.bazinga.domain.other.Address;
import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.domain.other.Supplier;
import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
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

        Game game1 = new Game();
        Game game2 = new Game();
        Game game3 = new Game();

        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.setDate("12/03/2018");
        salesReceipt.setItem(game1);
        salesReceipt.setSellingPrice(39.99);
        salesReceipt.setCustomer(customer);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setSupplier(supplier);
        purchaseReceipt.setDate("05/03/2018");
        purchaseReceipt.setPurchasePrice(15.59);
        purchaseReceipt.setItem(game1);


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
            gameToChange.setName(game.getName());
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
    public Iterable<Game> findOneByName(String name) {
        return repository.findOneByName(name);
    }
}

