package bg.mck.mapper;

import bg.mck.dto.CreateTransportDTO;
import bg.mck.entity.transportEntity.TransportEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    TransportEntity mapCreateTransportDtoToTransportEntity(CreateTransportDTO createTransportDTO);
}
