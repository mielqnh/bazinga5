package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
import be.mielnoelanders.bazinga.domain.enums.AccessoryType;
import be.mielnoelanders.bazinga.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    // FIELDS
    private final AccessoryRepository repository;

    // CONSTRUCTORS
    @Autowired
    public AccessoryServiceImpl(AccessoryRepository repository) {
        this.repository = repository;
    }

    // METHODS
    // --> init
    @PostConstruct
    public void init() {

        Accessory accessory1 = new Accessory();
        accessory1.setName("Dragon Shield 100 box");
        accessory1.setType(AccessoryType.SLEEVES);
        Accessory accessory2 = new Accessory();
        Accessory accessory3 = new Accessory();

        this.repository.saveAll(Arrays.asList(accessory1, accessory2, accessory3));
    }

    // --> create
    @Override
    public Accessory addOne(Accessory accessory) {
        return repository.save(accessory);
    }

    // --> read
    @Override
    public Iterable<Accessory> findAll() {
        return repository.findAll();
    }

    @Override
    public Accessory findOneById(Long id) {
        Optional<Accessory> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public Accessory updateOneById(Long id, Accessory accessory) {
        Accessory accessoryToChange = findOneById(id);
        if (accessoryToChange == null) {
            return null;
        } else {
            accessoryToChange.setName(accessory.getName());
            return repository.save(accessoryToChange);
        }
    }

    // --> delete
    @Override
    public Accessory deleteOneById(Long id) {
        Accessory accessory = findOneById(id);
        if (accessory == null) {
            return null;
        } else {
            repository.deleteById(id);
            return accessory;
        }
    }

    // --> others
    @Override
    public Iterable<Accessory> findOneByName(String name) {
        return repository.findOneByName(name);
    }
}

