package bg.mck.repository.material;

import bg.mck.entity.materialEntity.FastenerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FastenerRepository extends MongoRepository<FastenerEntity, String> {

    FastenerEntity findByName(String name);

    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    List<FastenerEntity> findByPartOfName(String materialName, Sort sort);
}
