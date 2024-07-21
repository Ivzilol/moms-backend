package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.UnspecifiedDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.UnspecifiedEntity;
import bg.mck.ordercommandservice.event.UnspecifiedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UnspecifiedMapper {

    UnspecifiedDTO toDTO(UnspecifiedEntity unspecifiedEntity);

    UnspecifiedEntity toEntity(UnspecifiedDTO unspecifiedDTO);

    UnspecifiedEvent toEvent(UnspecifiedEntity unspecifiedEntity);

    void toUpdateUnspecifiedEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget UnspecifiedEntity unspecifiedEntity);
}
