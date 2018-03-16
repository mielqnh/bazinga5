package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.other.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
