package bg.mck.mapper;

import bg.mck.dto.CreateConstructionDTO;
import bg.mck.entity.constructions.ConstructionSiteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionMapper {

    ConstructionSiteEntity mapCreateConstructionDtoToConstructionEntity(CreateConstructionDTO createConstructionDTO);
}
