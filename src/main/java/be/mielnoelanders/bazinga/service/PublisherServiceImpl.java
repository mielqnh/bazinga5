package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Publisher;
import be.mielnoelanders.bazinga.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);

    @Autowired
    private PublisherRepository repo;

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