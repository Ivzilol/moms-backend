package bg.mck.repository.material;

import bg.mck.dto.InsulationDTO;
import bg.mck.dto.MaterialDTO;
import bg.mck.entity.materialEntity.InsulationEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsulationRepository extends MongoRepository<InsulationEntity, String > {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'type' : 1, 'thickness' : 1, " +
                    "'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<InsulationDTO> findByPartOfName(String regex, Sort sort);
}
