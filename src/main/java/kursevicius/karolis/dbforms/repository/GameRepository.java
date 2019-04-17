package kursevicius.karolis.dbforms.repository;

import kursevicius.karolis.dbforms.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
