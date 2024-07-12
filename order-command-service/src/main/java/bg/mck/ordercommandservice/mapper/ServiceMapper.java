package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.ServiceDTO;
import bg.mck.ordercommandservice.entity.ServiceEntity;
import bg.mck.ordercommandservice.event.ServiceEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO toServiceDTO(ServiceEntity serviceEntity);

    ServiceEntity toServiceEntity(ServiceDTO serviceDTO);

    ServiceEvent toEvent(ServiceEntity serviceEntity);
}
