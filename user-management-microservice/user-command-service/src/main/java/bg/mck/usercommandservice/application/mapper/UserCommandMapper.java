package bg.mck.usercommandservice.application.mapper;

import bg.mck.usercommandservice.application.dto.UserCommandDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCommandMapper {

    UserEntity toEntity(UserCommandDTO userCommandDTO);
    UserCommandDTO toDTO(UserEntity userEntity);
}
