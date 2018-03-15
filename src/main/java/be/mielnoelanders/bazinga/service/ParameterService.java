package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;

public interface ParameterService {


// --> create (saveBla)



// --> read (findAllBla & findBlaById)



// --> update (updateBla)



// --> delete (deleteBlaById)



// --> others (findBlaByBuh)





    // Insert new Parameter //
    Parameter addParameter(Parameter parameter);

    // Get all parameters //
    Iterable<Parameter> getAll();

    // Find unique by parameter type //
    Parameter findByType(ParameterEnum type);

    // Update parameter by type //
    boolean updateParameterByType(Parameter parameter);

    // Delete parameter by id //
    boolean deleteParameter(Long id);
}