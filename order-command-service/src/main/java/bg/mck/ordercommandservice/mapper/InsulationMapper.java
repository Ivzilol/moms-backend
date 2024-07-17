package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.InsulationDTO;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.event.InsulationEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsulationMapper {

    InsulationDTO toDTO(InsulationEntity insulationEntity);

    InsulationEntity toEntity(InsulationDTO insulationDTO);

    InsulationEvent toEvent(InsulationEntity insulationEntity);
}