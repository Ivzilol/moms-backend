package bg.mck.ordercommandservice.mapper;


import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.event.FasterEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FastenerMapper {

    FastenerDTO toDTO(FastenerEntity fastenerEntity);

    FastenerEntity toEntity(FastenerDTO fastenerDTO);

    FasterEvent toEvent(FastenerEntity fastenerEntity);

    void toUpdateFasterEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget FastenerEntity fastenerEntity);
}
