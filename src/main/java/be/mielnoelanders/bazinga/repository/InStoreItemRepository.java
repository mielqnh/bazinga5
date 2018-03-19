package be.mielnoelanders.bazinga.repository;

import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InStoreItemRepository extends JpaRepository<PurchaseReceipt,Long>{

}
