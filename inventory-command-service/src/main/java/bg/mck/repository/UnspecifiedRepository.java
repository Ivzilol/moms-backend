package bg.mck.repository;

import bg.mck.entity.materialEntity.UnspecifiedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnspecifiedRepository extends JpaRepository<UnspecifiedEntity, Long> {
}
