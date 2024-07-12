package bg.mck.repository;

import bg.mck.entity.materialEntity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {
    SetEntity findByName(String s);
}
