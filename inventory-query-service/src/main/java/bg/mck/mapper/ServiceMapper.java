package bg.mck.mapper;

import bg.mck.dto.FastenersDTO;
import bg.mck.dto.ServiceDTO;
import bg.mck.entity.materialEntity.FastenerEntity;
import bg.mck.entity.serviceEntity.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceEntity toServiceEntity(ServiceDTO serviceDTO);
    ServiceDTO toServiceDTO(ServiceEntity serviceEntity);
    List<ServiceDTO> toServiceDTOs(List<ServiceEntity> serviceEntities);
}
