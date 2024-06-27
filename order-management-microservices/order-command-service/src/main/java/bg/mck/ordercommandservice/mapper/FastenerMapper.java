package bg.mck.ordercommandservice.mapper;


import bg.mck.ordercommandservice.dto.Material.FastenerDTO;
import bg.mck.ordercommandservice.entity.material.FastenerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastenerMapper {

    FastenerDTO toDTO(FastenerEntity fastenerEntity);

    FastenerEntity toEntity(FastenerDTO fastenerDTO);
}
