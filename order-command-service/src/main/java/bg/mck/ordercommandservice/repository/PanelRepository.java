package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelRepository extends JpaRepository<PanelEntity, Long> {
}
