package kursevicius.karolis.dbforms.repository;

import kursevicius.karolis.dbforms.karoliomodel.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
