package bg.mck.repository.transport;

import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends MongoRepository<TransportEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, " +
                    "'maxLength' : 1, 'weight' : 1, 'Truck' : 1, 'quantity' : 1," +
                    " 'description' : 1, 'specificationFileUrl' : 1 }")
    List<TransportEntity> findByPartOfName(String transportName, Sort sort);
}
