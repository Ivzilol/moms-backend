package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MetalDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.MetalEntity;
import bg.mck.ordercommandservice.event.MetalEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MetalMapper {

    MetalDTO toDTO(MetalEntity metalEntity);

    MetalEntity toEntity(MetalDTO metalDTO);
    MetalEvent toEvent(MetalEntity metalEntity);

    void toUpdateMetalEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget MetalEntity metalEntity);
}
