package bg.mck.usercommandservice.application.mapper;

import bg.mck.usercommandservice.application.dto.UserCommandDTO;
import bg.mck.usercommandservice.application.dto.UserUpdateProfileDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUpdateProfileMapper {

    UserEntity toEntity(UserUpdateProfileDTO userUpdateProfileDTO);
    UserUpdateProfileDTO toDTO(UserEntity userEntity);
}
