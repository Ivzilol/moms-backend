package bg.mck.repository;

import bg.mck.entity.materialEntity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelRepository extends JpaRepository<PanelEntity, Long> {
}
