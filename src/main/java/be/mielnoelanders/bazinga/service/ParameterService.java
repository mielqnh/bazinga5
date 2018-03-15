package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;

public interface ParameterService {

// --> create
    Parameter addOne(Parameter parameter);

// --> read
    Iterable<Parameter> findAll();

    Parameter findOneById(long id);

// --> update
    Parameter updateOneById(Long id, Parameter parameter);

// --> delete
    boolean deleteOneById(Long id);
}