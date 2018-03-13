package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.SupplierGamesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

public class SupplierGamesServiceImpl implements SupplierGamesService {


    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private SupplierGamesRepository repository;

    @PostConstruct
    public void init() {

        Publisher publisher = new Publisher();
        publisher.setName("Plaid Hat Games");
        publisher.setWebsite("http://www.plaidhatgames.com");

        Address address = new Address();
        address.setPostalCode("9300");
        address.setNumber("12");
        address.setStreet("Rue de la chapelle");
        address.setCity("Hasselt");
        address.setCountry("Belgie");

        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setName("Peeters");
        customer.setPhoneNumber("011 17 23 45");
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

        Game.Builder game2init = new Game.Builder();
        game2init.title("Dit is game 2")
                .edition(2)
                .publisher(publisher);
        Game game2 = game2init.build();

        Game.Builder game3init = new Game.Builder();
        game3init.title("Dit is game 3")
                .edition(3)
                .publisher(publisher);
        Game game3 = game3init.build();

        CustomerGames customerGames1 = new CustomerGames();
        customerGames1.setDate("12/03/2018");
        customerGames1.setGame(game1);
        customerGames1.setSellingPrice(39.99);
        customerGames1.setCustomer(customer);

        SupplierGames  supplierGames1 = new SupplierGames();
        supplierGames1.setSupplier(supplier);
        supplierGames1.setDate("05/03/2017");
        supplierGames1.setPurchasePrice(15.59);
        supplierGames1.setGame(game1);

        SupplierGames  supplierGames2 = new SupplierGames();
        supplierGames2.setSupplier(supplier);
        supplierGames2.setDate("11/11/2017");
        supplierGames2.setPurchasePrice(45.59);
        supplierGames2.setGame(game2);

        SupplierGames  supplierGames3 = new SupplierGames();
        supplierGames2.setSupplier(supplier);
        supplierGames2.setDate("13/08/2017");
        supplierGames2.setPurchasePrice(99.99);
        supplierGames2.setGame(game3);

        this.repository.saveAll(Arrays.asList(supplierGames1, supplierGames2, supplierGames3));

    }

    @Override
    public Iterable<SupplierGames> getAll() {
        return repository.findAll();
    }

    @Override
    public SupplierGames getOne(Long id) {
        Optional<SupplierGames> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public SupplierGames deleteById(Long id) {
        SupplierGames supplierGames = getOne(id);
        if (supplierGames == null) {
            return null;
        } else {
            repository.deleteById(id);
            return supplierGames;
        }
    }

    @Override
    public SupplierGames insertSupplierGames(SupplierGames supplierGames) {
        return repository.save(supplierGames);
    }

    @Override
    public SupplierGames changeSupplierGames(Long id, SupplierGames supplierGames) {
        SupplierGames supplierGamesToChange = getOne(id);
        if(supplierGamesToChange == null){
            return null;
        }else{
            supplierGamesToChange.setGame(supplierGames.getGame());
            return repository.save(supplierGamesToChange);
        }
    }
}
