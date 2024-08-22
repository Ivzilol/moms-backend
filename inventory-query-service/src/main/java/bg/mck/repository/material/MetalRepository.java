package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.dto.MetalDTO;
import bg.mck.entity.materialEntity.MetalEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetalRepository extends MongoRepository<MetalEntity, String> {
    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'totalWeight' : 1, " +
                    "'totalWeightUnit' : 1, 'kind' : 1, 'description' : 1 }")
    List<MetalDTO> findByPartOfName(String regex, Sort sort);

    MetalEntity findByName(String name);
}
