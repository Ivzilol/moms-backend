package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.InsulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulationRepository extends JpaRepository<InsulationEntity, Long> {
}
