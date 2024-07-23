package bg.mck.ordercommandservice.mapper;


import bg.mck.ordercommandservice.dto.FastenerDTO;
import bg.mck.ordercommandservice.entity.FastenerEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.FasterEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FastenerMapper {

    @Mappings({
            @Mapping(target = "length", expression = "java(splitLength(fastenerEntity.getLength())[0])"),
            @Mapping(target = "lengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(splitLength(fastenerEntity.getLength())[1]))")
    })
    FastenerDTO toDTO(FastenerEntity fastenerEntity);

    @Mapping(target = "length", expression = "java(concatenateLength(fastenerDTO.getLength(), fastenerDTO.getLengthUnit()))")
    FastenerEntity toEntity(FastenerDTO fastenerDTO);

    FasterEvent toEvent(FastenerEntity fastenerEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        return length + " " + lengthUnit;
    }

    default String[] splitLength(String length) {
        return length.split(" ");
    }
}

