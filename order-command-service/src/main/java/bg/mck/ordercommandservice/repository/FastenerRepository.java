package bg.mck.ordercommandservice.repository;

import bg.mck.ordercommandservice.entity.material.FastenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastenerRepository extends JpaRepository<FastenerEntity, Long>{

}
