package bg.mck.userqueryservice.application.mapper;

import bg.mck.userqueryservice.application.dto.UserCredentialsDTO;
import bg.mck.userqueryservice.application.dto.UserDetailsDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDetailsDTO userDetailsDTO);
    UserDetailsDTO toDTO(UserEntity userEntity);

    @Mapping(source = "password", target = "hashedPassword")
    UserCredentialsDTO toUserCredentialsDTO(UserEntity userEntity);

}
