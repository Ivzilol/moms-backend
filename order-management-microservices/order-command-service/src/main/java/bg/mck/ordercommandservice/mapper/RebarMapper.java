package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.Material.RebarDTO;
import bg.mck.ordercommandservice.entity.material.RebarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RebarMapper {

    RebarDTO toDTO(RebarEntity rebarEntity);

    RebarEntity toEntity(RebarDTO rebarDTO);
}
