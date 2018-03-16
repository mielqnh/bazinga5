package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpansionRepository extends JpaRepository<Expansion,Long> {

    Iterable<Expansion> findOneByName(String name);
}
