package bg.mck.ordercommandservice.mapper;


import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.event.FasterEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FastenerMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "length", expression = "java(split(fastenerEntity.getLength())[0])"),
            @Mapping(target = "lengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(fastenerEntity.getLength())[1]))")
    })
    FastenerDTO toDTO(FastenerEntity fastenerEntity);

    @Mapping(target = "length", expression = "java(concatenate(fastenerDTO.getLength(), fastenerDTO.getLengthUnit()))")
    FastenerEntity toEntity(FastenerDTO fastenerDTO);

    FasterEvent toEvent(FastenerEntity fastenerEntity);

}
