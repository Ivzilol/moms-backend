package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.UnspecifiedDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.UnspecifiedEntity;
import bg.mck.ordercommandservice.event.UnspecifiedEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UnspecifiedMapper extends Splitter, Concatenator {

    UnspecifiedDTO toDTO(UnspecifiedEntity unspecifiedEntity);

    UnspecifiedEntity toEntity(UnspecifiedDTO unspecifiedDTO);

    UnspecifiedEvent toEvent(UnspecifiedEntity unspecifiedEntity);
}
