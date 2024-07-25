package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.TransportDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.SetEntity;
import bg.mck.ordercommandservice.entity.TransportEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.TransportEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(transportEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(transportEntity.getMaxLength())[1]))"),
            @Mapping(target = "weight", expression = "java(split(transportEntity.getWeight())[0])"),
            @Mapping(target = "weightUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.WeightUnits.valueOf(split(transportEntity.getWeight())[1]))")
    })
    TransportDTO toTransportDTO(TransportEntity transportEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenateLength(transportDTO.getMaxLength(), transportDTO.getMaxLengthUnit()))"),
            @Mapping(target = "weight", expression = "java(concatenateWeight(transportDTO.getWeight(), transportDTO.getWeightUnit()))")
    })
    TransportEntity toTransportEntity(TransportDTO transportDTO);

    TransportEvent toEvent(TransportEntity transportEntity);

    void toUpdateTransportEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget TransportEntity transportEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        return length + " " + lengthUnit;
    }

    default String concatenateWeight(String length, WeightUnits weightUnits) {
        if (length == null && weightUnits == null) {
            return null;
        }
        if (length == null) {
            return weightUnits.toString();
        }
        if (weightUnits == null) {
            return length;
        }
        return length + " " + weightUnits;
    }

    default String[] split(String length) {
        if (length == null) {
            return null;
        }
        return length.split(" ");
    }
}
