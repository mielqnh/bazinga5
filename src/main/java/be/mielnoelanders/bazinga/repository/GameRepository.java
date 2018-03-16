package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.basicitems.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    Iterable<Game> findByTitle(String name);
}
