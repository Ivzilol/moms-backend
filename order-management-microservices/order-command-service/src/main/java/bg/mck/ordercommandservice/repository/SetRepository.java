package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {
}
