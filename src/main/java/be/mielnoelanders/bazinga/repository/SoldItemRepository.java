package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldItemRepository extends JpaRepository<SoldItem,Long>{

}
