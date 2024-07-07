package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.ConstructionSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSiteEntity, Long> {
    Optional<ConstructionSiteEntity> findByConstructionNumberAndName(String constructionNumber, String name);

    Optional<ConstructionSiteEntity> findByName(String name);
    Optional<ConstructionSiteEntity> findByConstructionNumber(String constructionNumber);
}
