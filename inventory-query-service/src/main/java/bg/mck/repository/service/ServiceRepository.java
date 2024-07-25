package bg.mck.repository.service;

import bg.mck.entity.serviceEntity.ServiceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends MongoRepository<ServiceEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, " +
                    "'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<ServiceEntity> findByPartOfName(String serviceName, Sort sort);
}
