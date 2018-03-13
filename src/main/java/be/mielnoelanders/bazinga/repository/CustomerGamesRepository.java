package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.CustomerGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerGamesRepository extends JpaRepository<CustomerGames,Long>{

}
