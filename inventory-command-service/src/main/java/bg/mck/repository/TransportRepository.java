package bg.mck.repository;

import bg.mck.entity.transportEntity.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<TransportEntity, Long> {

    TransportEntity findByName(String name);
}
