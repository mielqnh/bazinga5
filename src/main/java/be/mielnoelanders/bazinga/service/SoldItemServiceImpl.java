package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import be.mielnoelanders.bazinga.domain.other.Address;
import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.domain.other.Supplier;
import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
import be.mielnoelanders.bazinga.repository.SoldItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class SoldItemServiceImpl implements SoldItemService {

    // FIELDS
    private final SoldItemRepository repository;

    // CONSTRUCTORS
    @Autowired
    public SoldItemServiceImpl(SoldItemRepository repository) {
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

        Game game1 = new Game();
        Game game2 = new Game();
        Game game3 = new Game();

        SalesReceipt salesReceipt1 = new SalesReceipt();
        salesReceipt1.setDate("12/03/2018");
        salesReceipt1.setItem(game1);
        salesReceipt1.setSellingPrice(39.99);
        salesReceipt1.setCustomer(customer);

        SalesReceipt salesReceipt2 = new SalesReceipt();
        salesReceipt2.setDate("23/01/2018");
        salesReceipt2.setItem(game2);
        salesReceipt2.setSellingPrice(69.99);
        salesReceipt2.setCustomer(customer);

        SalesReceipt salesReceipt3 = new SalesReceipt();
        salesReceipt3.setDate("03/02/2018");
        salesReceipt3.setItem(game3);
        salesReceipt3.setSellingPrice(79.99);
        salesReceipt3.setCustomer(customer);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setSupplier(supplier);
        purchaseReceipt.setDate("05/03/2018");
        purchaseReceipt.setPurchasePrice(15.59);
        purchaseReceipt.setItem(game1);

        this.repository.saveAll(Arrays.asList(salesReceipt1, salesReceipt2, salesReceipt3));

    }

    // --> create
    @Override
    public SalesReceipt addOne(SalesReceipt salesReceipt) {
        return repository.save(salesReceipt);
    }

    // --> read
    @Override
    public Iterable<SalesReceipt> findAll() {
        return repository.findAll();
    }

    @Override
    public SalesReceipt findOneById(Long id) {
        Optional<SalesReceipt> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public SalesReceipt updateOneById(Long id, SalesReceipt salesReceipt) {
        SalesReceipt salesReceiptToChange = findOneById(id);
        if (salesReceiptToChange == null) {
            return null;
        } else {
            salesReceiptToChange.setDate(salesReceipt.getDate());
            return repository.save(salesReceiptToChange);
        }
    }


    // --> delete
    @Override
    public SalesReceipt deleteOneById(Long id) {
        SalesReceipt salesReceipt = findOneById(id);
        if (salesReceipt == null) {
            return null;
        } else {
            repository.deleteById(id);
            return salesReceipt;
        }
    }
}
