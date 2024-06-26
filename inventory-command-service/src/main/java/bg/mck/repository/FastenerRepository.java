package bg.mck.repository;

import bg.mck.entity.materialEntity.FastenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastenerRepository extends JpaRepository<FastenerEntity, Long> {

}
