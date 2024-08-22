package bg.mck.repository.material;

import bg.mck.dto.MaterialDTO;
import bg.mck.dto.PanelsDTO;
import bg.mck.entity.materialEntity.PanelEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanelRepository extends MongoRepository<PanelEntity, String> {

    @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }",
            fields = "{ 'id' : 1, 'name' : 1, 'type' : 1, 'color' : 1, 'length' : 1, 'lengthUnit' : 1, 'width' : 1, 'widthUnit' : 1, " +
                    "'totalThickness' : 1, 'totalThicknessUnit' : 1, 'frontSheetThickness' : 1, 'frontSheetThicknessUnit' : 1, 'backSheetThickness' : 1, 'backSheetThicknessUnit' : 1," +
                    "'quantity' : 1, 'description' : 1, 'specificationFileUrl' : 1 }")
    List<PanelsDTO> findByPartOfName(String regex, Sort sort);
}
