package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.domain.Publisher;
import be.mielnoelanders.bazinga.domain.Supplier;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository repository;
    @Autowired
    private Supplier supplier;
    @Autowired
    private Publisher publisher;


//    @PostConstruct
//    public void init(){
//        Game arkhamHorrorLCG = new Game();
//        arkhamHorrorLCG.setTitle("Arkham Horror LCG");
//        arkhamHorrorLCG.setEdition(1);
//        arkhamHorrorLCG.setExpansions(null);
//        arkhamHorrorLCG.setNormalPrice(39.99);
//        arkhamHorrorLCG.setPromotionPercentage(0);
//        arkhamHorrorLCG.setActualPrice(39.99);
//        arkhamHorrorLCG.setPublisher(publisher);
//        arkhamHorrorLCG.setSupplier(supplier);
//        this.repository.save(arkhamHorrorLCG);
//    }

    @Override
    public Iterable<Game> getAll() {
        return repository.findAll();
    }
}
