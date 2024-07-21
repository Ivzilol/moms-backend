package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.event.TransportEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    TransportDTO toTransportDTO(TransportEntity transportEntity);

    TransportEntity toTransportEntity(TransportDTO transportDTO);

    TransportEvent toEvent(TransportEntity transportEntity);

    void toUpdateTransportEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget TransportEntity transportEntity);
}
