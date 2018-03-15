package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.*;
import be.mielnoelanders.bazinga.repository.InStoreItemRepository;
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
public class InStoreItemServiceImpl implements InStoreItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    // --> create (addOne)



// --> read (findAll & findOneById)



// --> update (updateOneById)



// --> delete (deleteOneById)



// --> others (Bla)

    @Autowired
    private InStoreItemRepository repository;

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

        InStoreItem inStoreItem1 = new InStoreItem();
        inStoreItem1.setSupplier(supplier);
        inStoreItem1.setDate("05/03/2017");
        inStoreItem1.setPurchasePrice(15.59);
        inStoreItem1.setGame(game1);

        InStoreItem inStoreItem2 = new InStoreItem();
        inStoreItem2.setSupplier(supplier);
        inStoreItem2.setDate("11/11/2017");
        inStoreItem2.setPurchasePrice(45.59);
        inStoreItem2.setGame(game2);

        InStoreItem inStoreItem3 = new InStoreItem();
        inStoreItem2.setSupplier(supplier);
        inStoreItem2.setDate("13/08/2017");
        inStoreItem2.setPurchasePrice(99.99);
        inStoreItem2.setGame(game3);

        this.repository.saveAll(Arrays.asList(inStoreItem1, inStoreItem2, inStoreItem3));

    }

    @Override
    public Iterable<InStoreItem> getAll() {
        return repository.findAll();
    }

    @Override
    public InStoreItem getOne(Long id) {
        Optional<InStoreItem> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public InStoreItem deleteById(Long id) {
        InStoreItem inStoreItem = getOne(id);
        if (inStoreItem == null) {
            return null;
        } else {
            repository.deleteById(id);
            return inStoreItem;
        }
    }

    @Override
    public InStoreItem insertSupplierGames(InStoreItem inStoreItem) {
        return repository.save(inStoreItem);
    }

    @Override
    public InStoreItem changeSupplierGames(Long id, InStoreItem inStoreItem) {
        InStoreItem inStoreItemToChange = getOne(id);
        if(inStoreItemToChange == null){
            return null;
        }else{
            inStoreItemToChange.setDate(inStoreItem.getDate());
            return repository.save(inStoreItemToChange);
        }
    }
}
