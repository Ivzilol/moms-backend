package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.ServiceDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.ServiceEntity;
import bg.mck.ordercommandservice.event.ServiceEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends Splitter, Concatenator {

    ServiceDTO toServiceDTO(ServiceEntity serviceEntity);

    ServiceEntity toServiceEntity(ServiceDTO serviceDTO);

    ServiceEvent toEvent(ServiceEntity serviceEntity);

}
