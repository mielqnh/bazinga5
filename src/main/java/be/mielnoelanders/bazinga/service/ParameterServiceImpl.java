package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.repository.ParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private ParameterRepository repo;

    @Override
    public Parameter addParameter(Parameter parameter) {
        return repo.save(parameter);
    }

    @Override
    public Iterable<Parameter> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Iterable<Parameter> findByType(String type) {
        return repo.findByType(type);
    }

    @Override
    public boolean updateParameterByType(String type, Parameter parameter) {
        Parameter result = (Parameter) repo.findByType(type);

        if (result != null) {
            result.setType(parameter.getType());
            result.setPercentage(parameter.getPercentage());

            this.repo.save(result);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteParameter(long id) {
        if (this.repo.existsById(id)) {
            this.repo.deleteById(id);
            return true;
        }
        return false;
    }
}