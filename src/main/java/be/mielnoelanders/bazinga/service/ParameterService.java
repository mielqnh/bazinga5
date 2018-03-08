package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;

public interface ParameterService {

    // Insert new Parameter //
    Parameter addParameter(Parameter parameter);

    // Get all parameters //
    Iterable<Parameter> getAll();

    // Find unique by parameter type //
    Iterable<Parameter> findByType(String type);

    // Update parameter by type //
    boolean updateParameterByType(String type, Parameter parameter);

    // Delete parameter by id //
    boolean deleteParameter(long id);
}