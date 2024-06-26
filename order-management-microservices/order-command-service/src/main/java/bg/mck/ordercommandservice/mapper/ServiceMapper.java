package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.ServiceDTO;
import bg.mck.ordercommandservice.entity.service.ServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDTO toServiceDTO(ServiceEntity serviceEntity);
    ServiceEntity toServiceEntity(ServiceDTO serviceDTO);
}
