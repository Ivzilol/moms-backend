package bg.mck.repository.material;

import bg.mck.dto.FastenersDTO;
import bg.mck.dto.GalvanisedDTO;
import bg.mck.entity.materialEntity.GalvanisedSheetEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalvanisedSheetRepository extends MongoRepository<GalvanisedSheetEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'type' : 1, 'maxLength' : 1, 'maxLengthUnit' : 1, " +
                    "'numberOfSheets' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<GalvanisedDTO> findByPartOfName(String regex, Sort sort);

    GalvanisedSheetEntity findByName(String name);
}
