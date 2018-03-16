package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.InStoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class InStoreItemServiceImpl implements InStoreItemService {

    // FIELDS
    private final InStoreItemRepository repository;

    // CONSTRUCTORS
    @Autowired
    public InStoreItemServiceImpl(InStoreItemRepository repository) {
        this.repository = repository;
    }

    // METHODS
    // --> init
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
        soldItem1.setItem(game1);
        soldItem1.setSellingPrice(39.99);
        soldItem1.setCustomer(customer);

        InStoreItem inStoreItem1 = new InStoreItem();
        inStoreItem1.setSupplier(supplier);
        inStoreItem1.setDate("05/03/2017");
        inStoreItem1.setPurchasePrice(15.59);
        inStoreItem1.setItem(game1);

        InStoreItem inStoreItem2 = new InStoreItem();
        inStoreItem2.setSupplier(supplier);
        inStoreItem2.setDate("11/11/2017");
        inStoreItem2.setPurchasePrice(45.59);
        inStoreItem2.setItem(game2);

        InStoreItem inStoreItem3 = new InStoreItem();
        inStoreItem2.setSupplier(supplier);
        inStoreItem2.setDate("13/08/2017");
        inStoreItem2.setPurchasePrice(99.99);
        inStoreItem2.setItem(game3);

        this.repository.saveAll(Arrays.asList(inStoreItem1, inStoreItem2, inStoreItem3));

    }

    // --> create
    @Override
    public InStoreItem addOne(InStoreItem inStoreItem) {
        return repository.save(inStoreItem);
    }

    // --> read
    @Override
    public Iterable<InStoreItem> findAll() {
        return repository.findAll();
    }

    @Override
    public InStoreItem findOneById(Long id) {
        Optional<InStoreItem> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public InStoreItem updateOneById(Long id, InStoreItem inStoreItem) {
        InStoreItem inStoreItemToChange = findOneById(id);
        if (inStoreItemToChange == null) {
            return null;
        } else {
            inStoreItemToChange.setDate(inStoreItem.getDate());
            return repository.save(inStoreItemToChange);
        }
    }

    // --> delete
    @Override
    public InStoreItem deleteOneById(Long id) {
        InStoreItem inStoreItem = findOneById(id);
        if (inStoreItem == null) {
            return null;
        } else {
            repository.deleteById(id);
            return inStoreItem;
        }
    }
}
