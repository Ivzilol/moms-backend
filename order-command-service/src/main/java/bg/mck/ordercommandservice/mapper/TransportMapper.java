package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.event.TransportEvent;
import bg.mck.ordercommandservice.mapper.util.Concatenator;
import bg.mck.ordercommandservice.mapper.util.Splitter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransportMapper extends Splitter, Concatenator {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(transportEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(transportEntity.getMaxLength())[1]))"),
            @Mapping(target = "weight", expression = "java(split(transportEntity.getWeight())[0])"),
            @Mapping(target = "weightUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(transportEntity.getWeight())[1]))")
    })
    TransportDTO toTransportDTO(TransportEntity transportEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenate(transportDTO.getMaxLength(), transportDTO.getMaxLengthUnit()))"),
            @Mapping(target = "weight", expression = "java(concatenate(transportDTO.getWeight(), transportDTO.getWeightUnit()))")
    })
    TransportEntity toTransportEntity(TransportDTO transportDTO);

    TransportEvent toEvent(TransportEntity transportEntity);

}
