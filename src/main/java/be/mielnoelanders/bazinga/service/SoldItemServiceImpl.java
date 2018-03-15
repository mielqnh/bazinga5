package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.SoldItemRepository;
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
public class SoldItemServiceImpl implements SoldItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private SoldItemRepository repository;

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

        SoldItem soldItem1 = new SoldItem();
        soldItem1.setDate("12/03/2018");
        soldItem1.setGame(game1);
        soldItem1.setSellingPrice(39.99);
        soldItem1.setCustomer(customer);

        SoldItem soldItem2 = new SoldItem();
        soldItem2.setDate("23/01/2018");
        soldItem2.setGame(game2);
        soldItem2.setSellingPrice(69.99);
        soldItem2.setCustomer(customer);

        SoldItem soldItem3 = new SoldItem();
        soldItem3.setDate("03/02/2018");
        soldItem3.setGame(game3);
        soldItem3.setSellingPrice(79.99);
        soldItem3.setCustomer(customer);

        InStoreItem inStoreItem = new InStoreItem();
        inStoreItem.setSupplier(supplier);
        inStoreItem.setDate("05/03/2018");
        inStoreItem.setPurchasePrice(15.59);
        inStoreItem.setGame(game1);

        this.repository.saveAll(Arrays.asList(soldItem1, soldItem2, soldItem3));

    }

    @Override
    public Iterable<SoldItem> getAll() {
            return repository.findAll();
    }

    @Override
    public SoldItem getOne(Long id) {
        Optional<SoldItem> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public SoldItem deleteById(Long id) {
        SoldItem soldItem = getOne(id);
        if (soldItem == null) {
            return null;
        } else {
            repository.deleteById(id);
            return soldItem;
        }
    }

    @Override
    public SoldItem saveCustomerGames(SoldItem soldItem) {
        return repository.save(soldItem);
    }

    @Override
    public SoldItem changeCustomerGames(Long id, SoldItem soldItem) {
        SoldItem soldItemToChange = getOne(id);
        if(soldItemToChange == null){
            return null;
        }else{
            soldItemToChange.setDate(soldItem.getDate());
            return repository.save(soldItemToChange);
        }
    }
}
