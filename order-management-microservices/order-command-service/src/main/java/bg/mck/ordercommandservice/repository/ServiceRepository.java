package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.service.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{
}
