package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.other.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByName(String name);
}