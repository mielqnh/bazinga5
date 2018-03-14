package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.CustomerGamesRepository;
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
public class CustomerGamesServiceImpl implements CustomerGamesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private CustomerGamesRepository repository;

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

        CustomerGames customerGames2 = new CustomerGames();
        customerGames2.setDate("23/01/2018");
        customerGames2.setGame(game2);
        customerGames2.setSellingPrice(69.99);
        customerGames2.setCustomer(customer);

        CustomerGames customerGames3 = new CustomerGames();
        customerGames3.setDate("03/02/2018");
        customerGames3.setGame(game3);
        customerGames3.setSellingPrice(79.99);
        customerGames3.setCustomer(customer);

        SupplierGames  supplierGames = new SupplierGames();
        supplierGames.setSupplier(supplier);
        supplierGames.setDate("05/03/2018");
        supplierGames.setPurchasePrice(15.59);
        supplierGames.setGame(game1);

        this.repository.saveAll(Arrays.asList(customerGames1, customerGames2, customerGames3));

    }

    @Override
    public Iterable<CustomerGames> getAll() {
            return repository.findAll();
    }

    @Override
    public CustomerGames getOne(Long id) {
        Optional<CustomerGames> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public CustomerGames deleteById(Long id) {
        CustomerGames customerGames = getOne(id);
        if (customerGames == null) {
            return null;
        } else {
            repository.deleteById(id);
            return customerGames;
        }
    }

    @Override
    public CustomerGames insertCustomerGames(CustomerGames customerGames) {
        return repository.save(customerGames);
    }

    @Override
    public CustomerGames changeCustomerGames(Long id, CustomerGames customerGames) {
        CustomerGames customerGamesToChange = getOne(id);
        if(customerGamesToChange == null){
            return null;
        }else{
            customerGamesToChange.setDate(customerGames.getDate());
            return repository.save(customerGamesToChange);
        }
    }
}
