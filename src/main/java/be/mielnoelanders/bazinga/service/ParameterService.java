package be.mielnoelanders.bazinga.service;

        import be.mielnoelanders.bazinga.domain.Parameter;

        import java.util.Optional;

public interface ParameterService {

    // Insert new Parameter //
    Boolean addParameter();

    // Get all parameters //
    Iterable<Parameter> getAll();

    // Get unique by parameter type //
    Optional<Parameter> getByType();

    // Update parameter by type //
    Boolean updateParameterByType();

    // Delete parameter by type //
    Boolean deleteParameterbyType();

}