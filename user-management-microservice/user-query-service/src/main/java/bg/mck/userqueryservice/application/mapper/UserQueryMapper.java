package bg.mck.userqueryservice.application.mapper;

import bg.mck.userqueryservice.application.dto.UserLoginResponseDTO;
import bg.mck.userqueryservice.application.dto.UserQueryDTO;
import bg.mck.userqueryservice.application.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserQueryMapper {

    UserEntity toEntity(UserQueryDTO userQueryDTO);

    UserQueryDTO toDTO(UserEntity userQueryEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "authorities" , target = "authorities")
    })
    UserLoginResponseDTO toUserResponseDTO(UserEntity userEntity);
}
