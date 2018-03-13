package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import be.mielnoelanders.bazinga.domain.Publisher;
import be.mielnoelanders.bazinga.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);

    @Autowired
    private PublisherRepository repo;

    @PostConstruct
    public void init(){

        Publisher publisher1 = new Publisher();
            publisher1.setName("What The What");
            publisher1.setWebsite("www.whatthewhat.com");
        Publisher publisher2 = new Publisher();
            publisher2.setName("Who The Who");
            publisher2.setWebsite("www.whothewho.com");
        Publisher publisher3 = new Publisher();
            publisher3.setName("Where The Where");
            publisher3.setWebsite("www.wherethewhere.com");

        this.repo.saveAll(Arrays.asList(publisher1,publisher2,publisher3));
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return repo.save(publisher);
    }

    @Override
    public Iterable<Publisher> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Publisher findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public boolean updatePublisherByName(Publisher publisher) {
        Publisher result = (Publisher) repo.findByName(publisher.getName());

        if (result != null) {
            result.setName(publisher.getName());
            result.setWebsite(publisher.getWebsite());

            this.repo.save(result);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePublisher(long id) {
        if (this.repo.existsById(id)) {
            this.repo.deleteById(id);
            return true;
        }
        return false;
    }
}