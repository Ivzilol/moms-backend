package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSiteEntity, Long> {
}
