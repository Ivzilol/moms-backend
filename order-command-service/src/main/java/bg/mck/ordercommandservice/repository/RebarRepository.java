package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.RebarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebarRepository extends JpaRepository<RebarEntity, Long> {
}
