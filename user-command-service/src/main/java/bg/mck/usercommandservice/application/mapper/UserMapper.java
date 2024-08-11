package bg.mck.usercommandservice.application.mapper;

import bg.mck.usercommandservice.application.dto.UserUpdatedDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserUpdatedDTO userUpdatedDTO);
    UserUpdatedDTO toDTO(UserEntity userEntity);
}
