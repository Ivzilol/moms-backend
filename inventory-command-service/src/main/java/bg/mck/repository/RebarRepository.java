package bg.mck.repository;

import bg.mck.entity.materialEntity.RebarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebarRepository extends JpaRepository<RebarEntity, Long> {
}
