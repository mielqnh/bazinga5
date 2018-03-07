package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter,Long> {

    // Get parameter by type //
    Optional<Parameter> findOne();

    Boolean save();
}