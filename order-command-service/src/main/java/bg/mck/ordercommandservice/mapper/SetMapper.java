package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.SetDTO;
import bg.mck.ordercommandservice.entity.SetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SetMapper {

    SetDTO toDTO(SetEntity setEntity);

    SetEntity toEntity(SetDTO setDTO);
}
