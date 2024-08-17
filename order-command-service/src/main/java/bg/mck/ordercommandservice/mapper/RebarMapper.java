package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.RebarDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.RebarEntity;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.entity.enums.WeightUnits;
import bg.mck.ordercommandservice.event.RebarEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RebarMapper {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(rebarEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(rebarEntity.getMaxLength())[1]))"),
    })
    RebarDTO toDTO(RebarEntity rebarEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenateLength(rebarDTO.getMaxLength(), rebarDTO.getMaxLengthUnit()))"),
    })
    RebarEntity toEntity(RebarDTO rebarDTO);

    RebarEvent toEvent(RebarEntity rebarEntity);

    void toUpdateRebarEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget RebarEntity rebarEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        return length + " " + lengthUnit;
    }

    default String concatenateWeight(String unit, WeightUnits unitType) {
        if (unit == null && unitType == null) {
            return null;
        }
        if (unit == null) {
            return unitType.toString();
        }
        if (unitType == null) {
            return unit;
        }
        return unit + " " + unitType;
    }

    default String[] split(String length) {
        if (length == null) {
            return null;
        }
        return length.split(" ");
    }
}
