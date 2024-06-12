package bg.mck.usercommandservice.application.mapper;

import bg.mck.usercommandservice.application.dto.UserCommandDTO;
import bg.mck.usercommandservice.application.entity.UserCommandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCommandMapper {

    UserCommandEntity toEntity(UserCommandDTO userCommandDTO);
    UserCommandDTO toDTO(UserCommandEntity userCommandEntity);
}
