package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.RebarDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.event.RebarEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RebarMapper {

    RebarDTO toDTO(RebarEntity rebarEntity);

    RebarEntity toEntity(RebarDTO rebarDTO);

    RebarEvent toEvent(RebarEntity rebarEntity);

    void toUpdateRebarEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget RebarEntity rebarEntity);
}
