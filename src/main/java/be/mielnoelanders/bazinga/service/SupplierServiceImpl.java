package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.other.Supplier;
import be.mielnoelanders.bazinga.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    // FIELDS
    private SupplierRepository supplierRepository;

    // CONSTRUCTORS
    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // METHODS
    // --> init
    @PostConstruct
    private void init() {
        Supplier supplier1 = new Supplier();
        supplier1.setName("suppl1_name");
        supplier1.setWebsite("suppl1_website");
        supplier1.setPhoneNumber("suppl1_phonenumber");
        Supplier supplier2 = new Supplier();
        supplier2.setName("suppl2_name");
        supplier2.setWebsite("suppl2_website");
        supplier2.setPhoneNumber("suppl2_phonenumber");
        Supplier supplier3 = new Supplier();
        supplier3.setName("suppl3_name");
        supplier3.setWebsite("suppl3_website");
        supplier3.setPhoneNumber("suppl3_phonenumber");
        supplierRepository.saveAll(Arrays.asList(supplier1, supplier2, supplier3));
    }

    // --> create
    @Override
    public Supplier addOne(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // --> read
    @Override
    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findOneById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    // --> update
    @Override
    public Supplier updateOneById(Long id, Supplier supplier) {
        Optional<Supplier> foundSupplier = supplierRepository.findById(id);
        if (foundSupplier.isPresent()) {
            Supplier supplierToUpdate = foundSupplier.get();
            supplierToUpdate.setName(supplier.getName());
            supplierToUpdate.setEmail(supplier.getEmail());
            supplierToUpdate.setPhoneNumber(supplier.getPhoneNumber());
            supplierToUpdate.setWebsite(supplier.getWebsite());
            return supplierRepository.save(supplierToUpdate);
        } else {
            return null;
        }
    }

    // --> delete
    @Override
    public void deleteOneById(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        }
    }
}
