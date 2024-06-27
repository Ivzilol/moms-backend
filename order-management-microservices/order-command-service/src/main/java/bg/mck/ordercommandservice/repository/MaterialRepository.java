package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<_MaterialEntity, Long> {
}
