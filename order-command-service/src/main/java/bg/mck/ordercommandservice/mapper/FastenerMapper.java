package bg.mck.ordercommandservice.mapper;


import bg.mck.ordercommandservice.entity.FastenerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastenerMapper {

    bg.mck.ordercommandservice.dto.Material.FastenerDTO toDTO(FastenerEntity fastenerEntity);

    FastenerEntity toEntity(bg.mck.ordercommandservice.dto.Material.FastenerDTO fastenerDTO);
}
