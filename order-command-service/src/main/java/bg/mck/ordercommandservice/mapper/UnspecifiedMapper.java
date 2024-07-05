package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.Material.UnspecifiedDTO;
import bg.mck.ordercommandservice.entity.UnspecifiedEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnspecifiedMapper {

    UnspecifiedDTO toDTO(UnspecifiedEntity unspecifiedEntity);

    UnspecifiedEntity toEntity(UnspecifiedDTO unspecifiedDTO);
}
