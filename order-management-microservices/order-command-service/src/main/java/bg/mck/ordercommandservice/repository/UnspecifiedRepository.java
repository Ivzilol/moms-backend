package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.UnspecifiedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnspecifiedRepository extends JpaRepository<UnspecifiedEntity, Long> {
}
