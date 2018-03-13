package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.SupplierGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierGamesRepository extends JpaRepository<SupplierGames,Long>{

}
