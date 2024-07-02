package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.transport.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<TransportEntity, Long>{
}
