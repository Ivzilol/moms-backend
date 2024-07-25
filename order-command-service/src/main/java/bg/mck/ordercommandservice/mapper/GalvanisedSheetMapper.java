package bg.mck.ordercommandservice.mapper;

import bg.mck.ordercommandservice.dto.GalvanisedSheetDTO;
import bg.mck.ordercommandservice.dto.UpdateOrderDTO;
import bg.mck.ordercommandservice.entity.GalvanisedSheetEntity;
import bg.mck.ordercommandservice.entity.enums.AreaUnits;
import bg.mck.ordercommandservice.entity.enums.LengthUnits;
import bg.mck.ordercommandservice.event.GalvanisedSheetEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GalvanisedSheetMapper {

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(split(galvanisedSheetEntity.getMaxLength())[0])"),
            @Mapping(target = "maxLengthUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.LengthUnits.valueOf(split(galvanisedSheetEntity.getMaxLength())[1]))"),
            @Mapping(target = "area", expression = "java(split(galvanisedSheetEntity.getArea())[0])"),
            @Mapping(target = "areaUnit", expression = "java(bg.mck.ordercommandservice.entity.enums.AreaUnits.valueOf(split(galvanisedSheetEntity.getArea())[1]))")
    })
    GalvanisedSheetDTO toDTO(GalvanisedSheetEntity galvanisedSheetEntity);

    @Mappings({
            @Mapping(target = "maxLength", expression = "java(concatenateLength(galvanisedSheetDTO.getMaxLength(), galvanisedSheetDTO.getMaxLengthUnit()))"),
            @Mapping(target = "area", expression = "java(concatenateArea(galvanisedSheetDTO.getArea(), galvanisedSheetDTO.getAreaUnit()))")
    })
    GalvanisedSheetEntity toEntity(GalvanisedSheetDTO galvanisedSheetDTO);

    GalvanisedSheetEvent toEvent(GalvanisedSheetEntity galvanisedSheetEntity);

    void toUpdateGalvanisedSheetEntity(UpdateOrderDTO updateOrderDTO, @MappingTarget GalvanisedSheetEntity galvanisedSheetEntity);

    default String concatenateLength(String unit, LengthUnits unitType) {
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

    default String[] split(String unit) {
        if (unit == null) {
            return null;
        }
        return unit.split(" ");
    }
}
