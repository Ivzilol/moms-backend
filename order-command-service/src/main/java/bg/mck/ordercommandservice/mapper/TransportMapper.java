package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.entity.transport.TransportEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransportMapper {
    TransportDTO toTransportDTO(TransportEntity transportEntity);
    TransportEntity toTransportEntity(TransportDTO transportDTO);
}
