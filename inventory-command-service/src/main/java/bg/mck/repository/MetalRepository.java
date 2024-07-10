package bg.mck.repository;

import bg.mck.entity.materialEntity.MetalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalRepository extends JpaRepository<MetalEntity, Long> {
    MetalEntity findByName(String description);
}
