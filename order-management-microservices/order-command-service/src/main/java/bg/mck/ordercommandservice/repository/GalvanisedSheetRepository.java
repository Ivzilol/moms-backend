package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.GalvanisedSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalvanisedSheetRepository extends JpaRepository<GalvanisedSheetEntity, Long>{
}
