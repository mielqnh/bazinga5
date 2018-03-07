package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import be.mielnoelanders.bazinga.repository.ParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ParameterServiceImpl  implements ParameterService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private ParameterRepository repo;

    public Parameter addParameter(Parameter parameter) {
        return repo.save(parameter);
    }

    @Override
    public Iterable<Parameter> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Parameter> getByType(ParameterEnum type) {
//        return repo.findOne(type);
        return null;
    }

    @Override
    public boolean updateParameterByType(String type, Parameter parameter) {
//        Parameter parameterDb = this.repo.findOne(type);
//
//        if (parameterDb != null) {
//            parameterDb.setType(parameter.getType());
//            parameterDb.setPercentage(parameter.getPercentage());
//
//            this.repo.save(parameterDb);
//            return true;
//        }

        return false;
    }

    @Override
    public boolean deleteParameter(long id) {
//        if (this.repo.exists(id)) {
//            this.repo.delete(id);
//            return true;
//        }
        return false;
    }
}