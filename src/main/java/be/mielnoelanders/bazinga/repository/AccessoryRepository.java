package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory,Long> {

    Iterable<Accessory> findOneByName(String name);
}
