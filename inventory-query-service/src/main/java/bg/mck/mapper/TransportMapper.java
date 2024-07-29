package bg.mck.mapper;

import bg.mck.dto.ServiceDTO;
import bg.mck.dto.TransportDTO;
import bg.mck.entity.serviceEntity.ServiceEntity;
import bg.mck.entity.transportEntity.TransportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    TransportEntity toTransportEntity(TransportDTO transportDTO);
    TransportDTO toTransportDTO(TransportEntity transportEntity);
    List<TransportDTO> toTransportDTOs(List<TransportEntity> transportEntities);
}
