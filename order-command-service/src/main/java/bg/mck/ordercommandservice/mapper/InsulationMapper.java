package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.InsulationDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.InsulationEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.InsulationEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsulationMapper {

    @Mappings({
            @Mapping(target = "thickness", expression = "java(split(insulationEntity.getThickness())[0])"),
            @Mapping(target = "thicknessUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(insulationEntity.getThickness())[1]))"),
            @Mapping(target = "quantity", expression = "java(split(insulationEntity.getQuantity())[0])"),
            @Mapping(target = "quantityUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.AreaUnits.valueOf(split(insulationEntity.getQuantity())[1]))")
    })
    InsulationDTO toDTO(InsulationEntity insulationEntity);

    @Mappings({
    @Mapping(target = "thickness", expression = "java(concatenateLength(insulationDTO.getThickness(), insulationDTO.getThicknessUnit()))"),
    @Mapping(target = "quantity", expression = "java(concatenateArea(insulationDTO.getQuantity(), insulationDTO.getQuantityUnit()))")
    })
    InsulationEntity toEntity(InsulationDTO insulationDTO);

    InsulationEvent toEvent(InsulationEntity insulationEntity);

    void toUpdateInsulationEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget InsulationEntity insulationEntity);

    default String concatenateLength(String length, LengthUnits lengthUnit) {
        if (length == null && lengthUnit == null) {
            return null;
        }
        if (length == null) {
            return lengthUnit.toString();
        }
        if (lengthUnit == null) {
            return length;
        }
        return length + " " + lengthUnit;
    }

    default String[] split(String length) {
        if (length == null) {
            return null;
        }
        return length.split(" ");
    }

    default String concatenateArea(String unit, AreaUnits unitType) {
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

}
