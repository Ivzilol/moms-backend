package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.Material.InsulationDTO;
import bg.mck.ordercommandservice.entity.material.InsulationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsulationMapper {

    InsulationDTO toDTO(InsulationEntity insulationEntity);

    InsulationEntity toEntity(InsulationDTO insulationDTO);
}
