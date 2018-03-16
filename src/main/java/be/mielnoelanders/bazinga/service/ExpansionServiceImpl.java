package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import be.mielnoelanders.bazinga.repository.ExpansionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class ExpansionServiceImpl implements ExpansionService {

    // FIELDS
    private final ExpansionRepository repository;

    // CONSTRUCTORS
    @Autowired
    public ExpansionServiceImpl(ExpansionRepository repository) {
        this.repository = repository;
    }

    // METHODS
    // --> init
    @PostConstruct
    public void init() {

        Expansion expansion1 = new Expansion();
        expansion1.setName("Where Darkness Lives");
        Expansion expansion2 = new Expansion();
        Expansion expansion3 = new Expansion();

        this.repository.saveAll(Arrays.asList(expansion1, expansion2, expansion3));
    }

    // --> create
    @Override
    public Expansion addOne(Expansion expansion) {
        return repository.save(expansion);
    }

    // --> read
    @Override
    public Iterable<Expansion> findAll() {
        return repository.findAll();
    }

    @Override
    public Expansion findOneById(Long id) {
        Optional<Expansion> result = repository.findById(id);
        return result.orElse(null);
    }

    // --> update
    @Override
    public Expansion updateOneById(Long id, Expansion expansion) {
        Expansion expansionToChange = findOneById(id);
        if (expansionToChange == null) {
            return null;
        } else {
            expansionToChange.setName(expansion.getName());
            return repository.save(expansionToChange);
        }
    }

    // --> delete
    @Override
    public Expansion deleteOneById(Long id) {
        Expansion expansion = findOneById(id);
        if (expansion == null) {
            return null;
        } else {
            repository.deleteById(id);
            return expansion;
        }
    }

    // --> others
    @Override
    public Iterable<Expansion> findOneByName(String name) {
        return repository.findOneByName(name);
    }
}

