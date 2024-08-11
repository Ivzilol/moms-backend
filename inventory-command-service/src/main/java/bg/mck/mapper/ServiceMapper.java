package bg.mck.mapper;

import bg.mck.dto.CreateServiceDTO;
import bg.mck.entity.serviceEntity.ServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceEntity mapCreateServiceDtoToServiceEntity(CreateServiceDTO createServiceDTO);
}
