package be.mielnoelanders.bazinga.service;

        import be.mielnoelanders.bazinga.domain.Parameter;
        import be.mielnoelanders.bazinga.domain.ParameterEnum;

        import java.util.Optional;

public interface ParameterService {

    // Insert new Parameter //
    Parameter addParameter(Parameter parameter);

    // Get all parameters //
    Iterable<Parameter> getAll();

    // Get unique by parameter type //
    Optional<Parameter> getByType(ParameterEnum type);

    // Update parameter by type //
    boolean updateParameterByType(String type, Parameter parameter);

    // Delete parameter //
    boolean deleteParameter(long id);

}