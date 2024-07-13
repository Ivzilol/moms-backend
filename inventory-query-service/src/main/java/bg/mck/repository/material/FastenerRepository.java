package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FastenerRepository extends MongoRepository<FastenerEntity, String> {

    FastenerEntity findByName(String name);

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'type' : 1, 'diameter' : 1, 'length' : 1, 'model' : 1, " +
                    "'clazz' : 1, 'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<MaterialDTO> findByPartOfName(String materialName, Sort sort);
}
