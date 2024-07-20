package bg.mck.repository;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionRepository extends JpaRepository<ConstructionSiteEntity, Long> {
}
