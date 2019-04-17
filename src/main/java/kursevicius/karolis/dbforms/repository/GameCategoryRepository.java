package kursevicius.karolis.dbforms.repository;

import kursevicius.karolis.dbforms.model.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCategoryRepository extends JpaRepository<GameCategory, Long> {
}
