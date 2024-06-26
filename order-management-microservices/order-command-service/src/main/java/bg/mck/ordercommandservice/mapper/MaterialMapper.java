package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MaterialDTO;
import bg.mck.ordercommandservice.entity.material._MaterialEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialDTO toDTO(_MaterialEntity materialEntity);

    _MaterialEntity toEntity(MaterialDTO materialDTO);
}
