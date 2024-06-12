package bg.mck.userqueryservice.application.mapper;

import bg.mck.userqueryservice.application.dto.UserQueryDTO;
import bg.mck.userqueryservice.application.entity.UserQueryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserQueryMapper {

    UserQueryEntity toEntity(UserQueryDTO userQueryDTO);
    UserQueryDTO toDTO(UserQueryEntity userQueryEntity);
}
