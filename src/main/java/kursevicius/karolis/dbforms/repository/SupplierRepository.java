package kursevicius.karolis.dbforms.repository;

import kursevicius.karolis.dbforms.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
