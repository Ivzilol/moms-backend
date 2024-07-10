package bg.mck.repository;

import bg.mck.entity.materialEntity.InsulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulationRepository extends JpaRepository<InsulationEntity, Long> {

    InsulationEntity findByName(String s);
}
