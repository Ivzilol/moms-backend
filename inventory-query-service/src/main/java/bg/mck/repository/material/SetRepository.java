package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.dto.SetDTO;
import bg.mck.entity.materialEntity.SetEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends MongoRepository<SetEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'galvanisedSheetThickness' : 1, 'color' : 1, 'maxLength' : 1, " +
                    "'maxLengthUnit' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<SetDTO> findByPartOfName(String regex, Sort sort);

    SetEntity findByName(String name);
}
