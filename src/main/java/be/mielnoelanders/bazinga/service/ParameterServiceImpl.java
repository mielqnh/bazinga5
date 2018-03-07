package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.repository.ParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ParameterServiceImpl  implements ParameterService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private ParameterRepository repository;

    @Override
    public Boolean addParameter() {
        return repository.save();
    }

    @Override
    public Iterable<Parameter> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Parameter> getByType() {
        return repository.findOne();
    }

    @Override
    public Boolean updateParameterByType() {
        return null;
    }

    @Override
    public Boolean deleteParameterbyType() {
        return null;
    }
}
