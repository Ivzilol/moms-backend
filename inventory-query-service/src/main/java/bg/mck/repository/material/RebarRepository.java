package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.dto.RebarDTO;
import bg.mck.entity.materialEntity.RebarEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebarRepository extends MongoRepository<RebarEntity, String> {
    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'maxLength' : 1, 'weight' : 1, " +
                    "'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<RebarDTO> findByPartOfName(String regex, Sort sort);
}
