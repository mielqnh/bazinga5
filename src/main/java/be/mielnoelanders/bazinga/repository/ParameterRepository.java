package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.other.Parameter;
import be.mielnoelanders.bazinga.domain.enums.ParameterEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter,Long> {

    // Get parameter by type //
    Parameter findByType(ParameterEnum type);
}