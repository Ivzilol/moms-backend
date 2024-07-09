package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.MetalDTO;
import bg.mck.ordercommandservice.entity.MetalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetalMapper {

    MetalDTO toDTO(MetalEntity metalEntity);

    MetalEntity toEntity(MetalDTO metalDTO);
}
