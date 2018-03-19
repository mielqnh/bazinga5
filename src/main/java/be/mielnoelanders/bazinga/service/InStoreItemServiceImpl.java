package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import be.mielnoelanders.bazinga.domain.other.Address;
import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.domain.other.Supplier;
import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
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

        Game game1 = new Game();
        Game game2 = new Game();
        Game game3 = new Game();

        SalesReceipt salesReceipt1 = new SalesReceipt();
        salesReceipt1.setDate("12/03/2018");
        salesReceipt1.setItem(game1);
        salesReceipt1.setSellingPrice(39.99);
        salesReceipt1.setCustomer(customer);

        PurchaseReceipt purchaseReceipt1 = new PurchaseReceipt();
        purchaseReceipt1.setSupplier(supplier);
        purchaseReceipt1.setDate("05/03/2017");
        purchaseReceipt1.setPurchasePrice(15.59);
        purchaseReceipt1.setItem(game1);

        PurchaseReceipt purchaseReceipt2 = new PurchaseReceipt();
        purchaseReceipt2.setSupplier(supplier);
        purchaseReceipt2.setDate("11/11/2017");
        purchaseReceipt2.setPurchasePrice(45.59);
        purchaseReceipt2.setItem(game2);

        PurchaseReceipt purchaseReceipt3 = new PurchaseReceipt();
        purchaseReceipt2.setSupplier(supplier);
        purchaseReceipt2.setDate("13/08/2017");
        purchaseReceipt2.setPurchasePrice(99.99);
        purchaseReceipt2.setItem(game3);

        this.repository.saveAll(Arrays.asList(purchaseReceipt1, purchaseReceipt2, purchaseReceipt3));

    }

    // --> create
    @Override
    public PurchaseReceipt addOne(PurchaseReceipt purchaseReceipt) {
        return repository.save(purchaseReceipt);
    }

    // --> read
    @Override
    public Iterable<PurchaseReceipt> findAll() {
        return repository.findAll();
    }

    @Override
    public PurchaseReceipt findOneById(Long id) {
        Optional<PurchaseReceipt> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public PurchaseReceipt updateOneById(Long id, PurchaseReceipt purchaseReceipt) {
        PurchaseReceipt purchaseReceiptToChange = findOneById(id);
        if (purchaseReceiptToChange == null) {
            return null;
        } else {
            purchaseReceiptToChange.setDate(purchaseReceipt.getDate());
            return repository.save(purchaseReceiptToChange);
        }
    }

    // --> delete
    @Override
    public PurchaseReceipt deleteOneById(Long id) {
        PurchaseReceipt purchaseReceipt = findOneById(id);
        if (purchaseReceipt == null) {
            return null;
        } else {
            repository.deleteById(id);
            return purchaseReceipt;
        }
    }
}
