package bg.mck.repository.constructionSite;

import bg.mck.entity.constructions.ConstructionSiteEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionSiteRepository extends MongoRepository<ConstructionSiteEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, " +
                    "'constructionNumber' : 1}")
    List<ConstructionSiteEntity> findByPartOfName(String serviceName, Sort sort);
}
