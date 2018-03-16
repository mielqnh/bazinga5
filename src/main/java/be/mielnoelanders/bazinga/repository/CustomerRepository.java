package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.other.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{


}