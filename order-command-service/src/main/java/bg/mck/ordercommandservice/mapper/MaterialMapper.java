package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MaterialDTO;
import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialDTO toMaterialDTO(_MaterialEntity materialEntity);

    _MaterialEntity toMaterialEntity(MaterialDTO materialDTO);
}
