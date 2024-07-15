package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.dto.UnspecifiedDTO;
import bg.mck.entity.materialEntity.UnspecifiedEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnspecifiedRepository extends MongoRepository<UnspecifiedEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, " +
                    "'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<UnspecifiedDTO> findByPartOfName(String regex, Sort sort);
}
