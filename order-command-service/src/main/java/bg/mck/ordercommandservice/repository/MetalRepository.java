package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.MetalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalRepository extends JpaRepository<MetalEntity, Long> {
}
