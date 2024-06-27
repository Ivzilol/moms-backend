package bg.mck.usercommandservice.application.mapper;

import bg.mck.usercommandservice.application.dto.UserDetailsDTO;
import bg.mck.usercommandservice.application.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDetailsDTO userDetailsDTO);
    UserDetailsDTO toDTO(UserEntity userEntity);
}
