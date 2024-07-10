package bg.mck.repository;

import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalvanisedSheetRepository extends JpaRepository<GalvanisedSheetEntity, Long> {
    GalvanisedSheetEntity findByName(String type);
}
